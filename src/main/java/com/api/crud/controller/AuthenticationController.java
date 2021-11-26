package com.api.crud.controller;

import com.api.crud.config.JwtRequestFilter;
import com.api.crud.config.JwtTokenUtil;
import com.api.crud.entity.RefreshToken;
import com.api.crud.entity.Users;
import com.api.crud.event.OnUserLogoutSuccessEvent;
import com.api.crud.model.*;
import com.api.crud.service.RefreshTokenService;
import com.api.crud.service.UsersService;
import com.api.crud.service.user.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UsersService usersService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping(value = "/register")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody JwtRequest request, Errors errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>("User does not exist or empty request", HttpStatus.BAD_REQUEST);
//            return ResponseEntity.badRequest().body("User does not exist or empty request");
        }
        userDetailsService.save(request);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest, BindingResult result){
        if (result.hasErrors()){
                return ResponseEntity.badRequest().body("Invalid information");
            }

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        Users user = usersService.selectByName(authenticationRequest.getUsername());

        //Find existing refreshToken if exist and delete base on username
        List<RefreshToken> existRefreshTokenList = refreshTokenService.findByUserId(user.getId());
        for (RefreshToken existToken: existRefreshTokenList){
            refreshTokenService.delete(existToken);
        }

        // Then create to refreshToken
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authenticationRequest.getUsername());
        refreshTokenService.insert(refreshToken);

        //Prepare header to response
        HttpHeaders Header = new HttpHeaders();
        Header.add("Authorization","Bearer "+jwtTokenUtil.doGenerateToken(new HashMap<>(),user.userName));
        Header.add("X-Refresh-Token",refreshToken.getToken());
        return new ResponseEntity<>(Header,HttpStatus.OK);


        // cach 2:
//         authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new JwtResponse(token));

    }

//    private void authenticate(String username, String password) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//
//        }catch (BadCredentialsException e){
//            throw new BadCredentialsException("Incorrect username or password", e);
//        }
//    }
    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshJwtToken(@RequestHeader("X-Refresh-Token") String token){
        RefreshToken refreshToken = refreshTokenService.findByToken(token);
        refreshTokenService.verifyExpiration(refreshToken);

        Users users = usersService.selectById(refreshToken.getUserId());

        HttpHeaders accessTokenHeader = new HttpHeaders();
        accessTokenHeader.add("Authorization", "Bearer " + jwtTokenUtil.doGenerateToken(new HashMap<>(), users.userName));
        return new ResponseEntity<>(accessTokenHeader,HttpStatus.OK);
    }

    @PutMapping("/logout")
    public ResponseEntity<Object> logoutUser(@RequestHeader("Authorization") String accessToken){
        String username= null;
        String jwt = null;

        if (accessToken!=null &&accessToken.startsWith("Bearer ")){
            jwt= accessToken.substring(7);
            username = jwtTokenUtil.getUserNameFromToken(jwt);
        }

        Users user = usersService.selectByName(username);
        if (user!=null){
            refreshTokenService.delete(refreshTokenService.findByUserId(user.id).get(0));
        }
        LogOutRequest logOutRequest= new LogOutRequest(username,jwt);
        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(username,jwt,logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseEntity.ok(new SuccessMessage(true,"User has successfully logged out"));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Object> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePassRequest changePassRequest){
        String jwt = jwtRequestFilter.getJwt(request);
        String username= jwtTokenUtil.getUserNameFromToken(jwt);
        Users user;
        try{
            user = usersService.selectByName(username);
//            if (user==null){
//                return new ResponseEntity<>("User Not Found with -> username"+username,HttpStatus.BAD_REQUEST);
//            }
            boolean matches= encoder.matches(changePassRequest.getCurrentPassword(),user.getPassword());
            if(changePassRequest.getNewPassword()!=null){
                if(matches){
                    user.setPassword(encoder.encode(changePassRequest.getNewPassword()));
                    usersService.update(user);
                }else {
                    return new ResponseEntity<>(new ResponseMessage("No"),HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ResponseMessage("Yes"),HttpStatus.OK);
        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
