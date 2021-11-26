package com.api.crud.repository;

import com.api.crud.entity.Teachers;

import java.util.List;

public interface TeacherRepository {
    public Teachers selectById(Integer id);
    public Teachers selectByName(String name);
    public List<Teachers> selectAll();
    public int insert(Teachers teachers);
    public int update(Teachers teachers);
    public int delete(Teachers teachers);

}
