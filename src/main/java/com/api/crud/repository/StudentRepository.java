package com.api.crud.repository;

import com.api.crud.entity.Students;

import java.util.List;


public interface StudentRepository {
    public Students selectById(Integer id);
    public Students selectByName(String name);
    public List<Students> selectAll();
    public int insert(Students students);
    public int update(Students students);
    public int delete(Students students);
}
