package com.api.crud.controller;

import com.api.crud.entity.Users;
import com.api.crud.model.ChangePassRequest;
import com.api.crud.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.api.crud.utils.Utility.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public String url = "/users/";
    public String userName = "Chi Tien";
    public Integer id = 2;
    public Users usersPostObject = new Users(id,
            "Chi Tien",
            "123");
    public Users usersPutObject = new Users(id,
            "Chi Tien",
            "1234");

    // set up môi trường test, để loại bỏ security,
    // nếu ko set up, security sẽ yêu cầu đăng nhập và lấy token để có thể thực hiện request
    @BeforeEach
    private void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    @Order(2)
    public void postUsers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(usersPostObject);
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    // add user but the same object
    @Test
    @Order(3)
    public void postUsersButSameObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(usersPostObject);
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(4)
    public void selectAllUser() throws Exception {
        mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(5)
    public void selectById() throws Exception {
        mockMvc.perform(get("/users/get/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(6)
    public void selectByUserName() throws Exception {
        mockMvc.perform(get(url+userName)
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(7)
    public void updateUser()throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(usersPutObject);
        mockMvc.perform(put("/users/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    // updateUser but not found
    @Test
    @Order(8)
    public void updateUserButNotFound()throws Exception{
        Users userNotFound = new Users(3,
                "Chi Tien",
                "123");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userNotFound);
        mockMvc.perform(put("/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }


    @Test
    @Order(9)
    public void deleteUser() throws Exception{
        mockMvc.perform(delete(url+id)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    // delete user but not found
    @Test
    @Order(10)
    public void deleteUserButNotFound() throws Exception{
        mockMvc.perform(delete(url+"{id}",3L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
