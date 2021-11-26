package com.api.crud.service;


import com.api.crud.entity.Teachers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    public Integer id =3;
    public Teachers newTeacher= new Teachers(id,"Ms.Lola",
            32,
            "Female",
            "KSS.PH0",
            1);
    public Teachers updateTeacher= new Teachers(id,"Ms.Lola",
            30,
            "Female",
            "PPH.PH0",
            2);

    @Test
    @Order(1)
    public void contextLoad() throws Exception{
        assertThat(teacherService).isNotNull();
    }

    @Test
    @Order(2)
    public void selectAll() {
        teacherService.selectAll();
    }

    @Test
    @Order(3)
    public void selectById() {
        teacherService.selectById(1);
    }

    // select byName
    @Test
    @Order(4)
    public void getTeacherByName() throws Exception{
        teacherService.selectByName("Ms.Lola");
    }

    @Test
    @Order(5)
    public void insert() {
        teacherService.insert(newTeacher);
    }

    @Test
    @Order(6)
    public void update() {
        teacherService.update(updateTeacher);
    }

    @Test
    @Order(7)
    public void delete() {
        Teachers teachers = teacherService.selectById(id);
        teacherService.delete(teachers);
    }
}
