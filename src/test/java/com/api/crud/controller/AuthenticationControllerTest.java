package com.api.crud.controller;
import com.api.crud.entity.RefreshToken;
import com.api.crud.entity.Users;
import com.api.crud.model.ChangePassRequest;
import com.api.crud.model.JwtRequest;
import com.api.crud.service.RefreshTokenService;
import com.api.crud.service.UsersService;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static com.api.crud.utils.Utility.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public String url = "/users/";
    public String authURI = "/api/auth";
    public String accessTokenHeaderKey = "Authorization";
    public String username= "chitien";
    public String password="123456";
    public Integer id= 1;
    public Users usersPostObject = new Users(2,
            "chitu",
            "123");
//    public Users usersPutObject = new Users(2,
//            "vanhoang",
//            "1234");
    public JwtRequest userNewDto= new JwtRequest(5,"chitu","12345");
    public JwtRequest notValidNewDto = new JwtRequest(6,"","12345");
    public JwtRequest incorrectNewDto = new JwtRequest(6,"zxsdgsde","12345");

    public ChangePassRequest changePassRequest= new ChangePassRequest("123456","123456");
    public ChangePassRequest passRequestNotMatches= new ChangePassRequest("1123","123456");


    private String obtainAccessToken(String username, String password) throws Exception {
        String contentAsString = String.format("{ \"username\":\"%s\", \"password\":\"%s\" }", username, password);
        ResultActions resultActions = mockMvc.perform(post(authURI+"/login")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(contentAsString))
                .andDo(print())
                .andExpect(status().isOk());

        return resultActions.andReturn().getResponse().getHeader(accessTokenHeaderKey);
    }

    // Load Context
    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    //PostID without Token
    @Test
    @Order(2)
    public void givenNoToken_whenPostSecureRequest_thenUnauthorized() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(usersPostObject);
        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    //Post ID with Token
//    @Test
//    @Order(3)
//    public void givenToken_whenPostSecureRequest_thenOk() throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(usersPostObject);
//        String accessToken = obtainAccessToken(username,password);
//        mockMvc.perform(post(url)
//                    .header(accessTokenHeaderKey,accessToken)
//                    .contentType(APPLICATION_JSON)
//                    .accept(APPLICATION_JSON)
//                    .content(json))
//                .andExpect(status().isOk());
//    }

    // Sign Up
    @Test
    @Order(3)
    public void signUpUser()throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userNewDto);
        mockMvc.perform(post(authURI+"/register")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Sign Up when not valid
    @Test
    @Order(4)
    public void signUpUserWhenNotValid()throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(notValidNewDto);
        mockMvc.perform(post(authURI+"/register")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //Get All without Token
    @Test
    @Order(5)
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    //Get All with Token
    @Test
    @Order(6)
    public void givenToken_whenGetSecureRequest_thenOk() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(get(url)
                .header(accessTokenHeaderKey, accessToken)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Get ById with Token
    @Test
    @Order(7)
    public void getUserById() throws Exception{
        String accessToken= obtainAccessToken(username,password);
        mockMvc.perform(get(url+"/get/{id}",id)
                    .header(accessTokenHeaderKey,accessToken)
                    .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Put Id with Token
//    @Test
//    @Order(7)
//    public void putUserById() throws Exception{
//        String accessToken = obtainAccessToken(username,password);
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(usersPutObject);
//        mockMvc.perform(put(url+"/update")
//                .header(accessTokenHeaderKey,accessToken)
//                .contentType(APPLICATION_JSON)
//                .accept(APPLICATION_JSON)
//                .content(json))
//            .andDo(print())
//            .andExpect(status().isOk());
//    }

    //Delete by ID with Token
    @Test
    @Order(8)
    public void  deleteUserById() throws Exception{
        String accessToken = obtainAccessToken(username,password);
        mockMvc.perform(delete(url+"{id}",5L)
                    .header(accessTokenHeaderKey,accessToken)
                    .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Refresh token
    @Test
    @Order(9)
    public void refreshToken() throws Exception{
        Users user = usersService.selectByName(username);
        String token = refreshTokenService.selectTokenByUserId(user.getId());
        mockMvc.perform(post(authURI+"/refresh")
                .header("X-Refresh-Token",token)
                    .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Refresh token when token expiry
    @Test
    @Order(10)
    public void refreshTokenWhenTokenExpiry() throws Exception{
        Users user = usersService.selectByName("nguyehoang");
        String token = refreshTokenService.selectTokenByUserId(user.getId());
        mockMvc.perform(post(authURI+"/refresh")
                .header("X-Refresh-Token",token)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }


    //Change password with token
    @Test
    @Order(11)
    public void changePassword() throws Exception{
        String accessToken = obtainAccessToken(username,password);
        mockMvc.perform(put(authURI+"/changePassword")
                .header(accessTokenHeaderKey,accessToken)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(asJsonString(changePassRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Change password with token but not found user
//    @Test
//    @Order(10)
//    public void changePasswordNotWithToken() throws Exception{
//
//        mockMvc.perform(put(authURI+"/changePassword")
//                .header(accessTokenHeaderKey,accessToken)
//                .contentType(APPLICATION_JSON)
//                .accept(APPLICATION_JSON)
//                .content(asJsonString(changePassRequest)))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }

    //Change password with token but currentPassword not matches
    @Test
    @Order(12)
    public void changePasswordNotMatches() throws Exception{
        String accessToken = obtainAccessToken(username,password);
        mockMvc.perform(put(authURI+"/changePassword")
                .header(accessTokenHeaderKey,accessToken)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(asJsonString(passRequestNotMatches)))
                .andDo(print())
                .andExpect(status().isOk());
    }


    // Logout with token
    @Test
    @Order(13)
    public void logoutWithToken()throws Exception{
        String accessToken = obtainAccessToken(username,"123456");
        mockMvc.perform(put(authURI+"/logout")
                    .header(accessTokenHeaderKey,accessToken)
                    .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //login test when not valid
    @Test
    @Order(14)
    public void loginWhenNotValid()throws Exception{
        mockMvc.perform(post(authURI+"/login")
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON)
                    .content(asJsonString(notValidNewDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //login test when incorrect username or pass
    @Test
    @Order(15)
    public void loginWhenIncorrect()throws Exception{
        mockMvc.perform(post(authURI+"/login")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(asJsonString(incorrectNewDto)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}
