package com.api.crud.service.user;

import com.api.crud.entity.Users;
import com.api.crud.model.JwtRequest;
import com.api.crud.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Users user = usersRepository.selectByName(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: "+username);
        }
        return new User(user.getUserName(),user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }

    public int save(JwtRequest user){
        Users newUser= new Users();
        newUser.setId(user.getId());
        newUser.setUserName(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return usersRepository.insert(newUser);
    }
}
