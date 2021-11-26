package com.api.crud.controller;


import com.api.crud.entity.Students;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private StudentController studentController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public String url = "/students/";
    public Integer id =1;
    public String name="Chi Tien";
    public Students studentsPostObject = new Students(id,"Chi Tien",
            23,
            "male",
            "tien@gmail.com",
            "0981878950",
            1);
    public Students studentsUpdateObject = new Students(id,"Van Hoang",
            23,
            "female",
            "hoang@gmail.com",
            "0881878950",
            1);

    // set up môi trường test, để loại bỏ security,
    // nếu ko set up, security sẽ yêu cầu đăng nhập và lấy token để có thể thực hiện request
    @BeforeEach
    private void setUp() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void contextLoads() throws Exception{
       assertThat(studentController).isNotNull();
    }


    @Test
    @Order(2)
    public void addStudent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(studentsPostObject);
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
        .content(json).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getAllStudents() throws Exception{
        mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void selectByID()throws Exception {
        mockMvc.perform(get("/students/get/{id}",id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void selectByName()throws Exception {
        mockMvc.perform(get(url+name)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(6)
    public void updateStudent() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(studentsUpdateObject);
        mockMvc.perform(put("/students/update").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    // update Student but not found
    @Test
    @Order(7)
    public void updateStudentButNotFound() throws Exception{
        Students studentUpdate = new Students(3,"Chi Tien",
                23,
                "male",
                "tien@gmail.com",
                "0981878950",
                1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(studentUpdate);
        mockMvc.perform(put("/students/update").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    @Order(8)
    public void deleteStudent() throws Exception{
        mockMvc.perform(delete(url+id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // delete student but not found
    @Test
    @Order(9)
    public void deleteStudentButNotFound() throws Exception{
        mockMvc.perform(delete(url+"{id}",3L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}