package com.api.crud.service;

import com.api.crud.entity.University;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UniversityServiceTest {

    @Autowired
    UniversityService universityService;

    public Integer id=1;
    public University newUniversity = new University(id,
            "DH.Ha Noi",
            "Ha Noi");
    public University updateUniversity = new University(id,
            "DH.DA NANG",
            "DA NANG");

    @Test
    @Order(1)
    public void contextLoads() throws Exception{
        assertThat(universityService).isNotNull();
    }

    @Test
    @Order(2)
    public void selectAll() {
        universityService.selectAll();
    }

    @Test
    @Order(3)
    public void insert() {
        universityService.insert(newUniversity);
    }

    @Test
    @Order(4)
    public void selectById() {
        universityService.selectById(1);

    }

    // select byName
    @Test
    @Order(5)
    public void getUniversityByName() throws Exception{
        universityService.selectByName("DH.Ha Noi");
    }


    @Test
    @Order(5)
    public void update() {
        universityService.update(updateUniversity);
    }

    @Test
    @Order(6)
    public void delete() {
        universityService.delete(updateUniversity);
    }
}