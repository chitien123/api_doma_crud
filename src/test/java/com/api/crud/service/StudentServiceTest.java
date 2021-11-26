package com.api.crud.service;

import com.api.crud.entity.Students;
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
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;


    public Integer id = 1;
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
//     Load context

    @Test
    @Order(1)
    public  void contextLoads() throws Exception{
        assertThat(studentService).isNotNull();
    }

    @Test
    @Order(2)
    public void selectAllStudents(){
        studentService.selectAll();
    }
//    @Test
//    public void testFindAll() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    @Order(3)
    public void testCreateStudent(){
        studentService.insert(studentsPostObject);
    }

    //Get byId
    @Test
    @Order(4)
    public void getStudentById() throws Exception{
         studentService.selectById(1);
    }

    // select byName
    @Test
    @Order(5)
    public void getStudentByName() throws Exception{
        studentService.selectByName("Chi Tien");
    }

    // update
    @Test
    @Order(6)
    public void updateStudents() throws  Exception{
        studentService.update(studentsUpdateObject);
    }

    // delete
    @Test
    @Order(7)
    public void deleteStudents() throws Exception{
        studentService.delete(studentsUpdateObject);
    }

}
