package com.api.crud.controller;


import com.api.crud.entity.University;
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
public class UniversityControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UniversityController universityController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public String url = "/university/";
    public Integer id=1;
    String name="DH.Ha Noi";
    public University newUniversity = new University(id,
            "DH.Ha Noi",
            "Ha Noi");
    public University updateUniversity = new University(id,
            "DH.DA NANG",
            "DA NANG");


    // set up môi trường test, để loại bỏ security,
    // nếu ko set up, security sẽ yêu cầu đăng nhập và lấy token để có thể thực hiện request
    @BeforeEach
    private void setUp() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void contextLoads() throws Exception{
        assertThat(universityController).isNotNull();
    }


    @Test
    @Order(2)
    public void addUniversity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newUniversity);
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    public void getAllUniversity() throws Exception{
        mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void selectByID()throws Exception {
        mockMvc.perform(get("/university/get/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
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
    public void updateUniversity() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(updateUniversity);
        mockMvc.perform(put("/university/update").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    // update University when not found
    @Test
    @Order(7)
    public void updateUniversityWhenNotFound() throws Exception{
        University notFoundUniversity= new University(3,
                "DH.Ha Noi",
                "Ha Noi");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(notFoundUniversity);
        mockMvc.perform(put("/university/update").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    @Order(8)
    public void deleteUniversity() throws Exception{
        mockMvc.perform(delete("/university/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // delete but not found
    @Test
    @Order(9)
    public void deleteUniversityButNotFound() throws Exception{
        mockMvc.perform(delete("/university/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}