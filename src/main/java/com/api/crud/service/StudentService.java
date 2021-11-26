package com.api.crud.service;

import com.api.crud.entity.Students;

import java.util.List;

public interface StudentService {
    public Students selectById(Integer id);
    public Students selectByName(String name);
    public List<Students> selectAll();
    public int insert(Students students);
    public int update(Students students);
    public int delete(Students students);
}
