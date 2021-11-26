package com.api.crud.service;

import com.api.crud.entity.Teachers;

import java.util.List;

public interface TeacherService {
    public Teachers selectById(Integer id);
    public Teachers selectByName(String name);
    public List<Teachers> selectAll();
    public int insert(Teachers teachers);
    public int update(Teachers teachers);
    public int delete(Teachers teachers);
}
