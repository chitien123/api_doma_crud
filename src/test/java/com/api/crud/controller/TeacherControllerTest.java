package com.api.crud.controller;

import com.api.crud.entity.Teachers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TeacherController teacherController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public String url = "/teacher/";
    public Integer id = 3;
    public String name = "Ms.Lola";
    public Teachers newTeacher = new Teachers(id, "Ms.Lola",
            32,
            "Female",
            "KSS.PH0",
            1);
    public Teachers updateTeacher = new Teachers(id, "Ms.Lola",
            30,
            "Female",
            "PPH.PH0",
            2);
    public Teachers notFoundTeacher = new Teachers(5, "Ms.Lola",
            30,
            "Female",
            "PPH.PH0",
            2);


    // set up môi trường test, để loại bỏ security,
    // nếu ko set up, security sẽ yêu cầu đăng nhập và lấy token để có thể thực hiện request
    @BeforeEach
    private void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        assertThat(teacherController).isNotNull();
    }


    @Test
    @Order(2)
    public void addTeachers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newTeacher);
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    public void getAllTeacher() throws Exception {
        mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void selectByID() throws Exception {
        mockMvc.perform(get("/teacher/get/{id}", 3L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(5)
    public void selectByName() throws Exception {
        mockMvc.perform(get(url + name)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(6)
    public void updateTeacher() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(updateTeacher);
        mockMvc.perform(put("/teacher/update").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    // update when not found teacher
    @Test
    @Order(7)
    public void updateWhenNotFoundTeacher() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(notFoundTeacher);
        mockMvc.perform(put("/teacher/update").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    @Order(8)
    public void deleteTeacher() throws Exception {
        mockMvc.perform(delete(url + id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Delete when not found
    @Test
    @Order(9)
    public void deleteWhenNotFoundTeacher() throws Exception {
        mockMvc.perform(delete(url + "{id}",5L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}