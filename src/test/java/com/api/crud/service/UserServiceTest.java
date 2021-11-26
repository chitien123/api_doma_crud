package com.api.crud.service;

import com.api.crud.entity.Users;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UsersService usersService;

    public Integer id=4;
    public Users usersPostObject = new Users(id,
            "Chi Tien",
            "123");
    public Users usersPutObject = new Users(id,
            "Pham Hoang",
            "1234");

    @Test
    @Order(1)
    public void contextLoads() throws Exception{
        assertThat(usersService).isNotNull();
    }

    @Test
    @Order(2)
    public void selectAllUser(){
        usersService.selectAll();
    }

    @Test
    @Order(3)
    public void createUser(){
        usersService.insert(usersPostObject);
    }

    //Get byId
    @Test
    @Order(4)
    public void getUserById(){
        usersService.selectById(id);
    }

    //get byUserName
    @Test
    @Order(5)
    public void getUserByUserName(){
        usersService.selectByName("chitien");
    }

    // update
    @Test
    @Order(6)
    public void updateUser(){
        usersService.update(usersPutObject);
    }

    //delete
    @Test
    @Order(7)
    public void deleteStudents(){
        usersService.delete(usersPutObject);
    }
}
