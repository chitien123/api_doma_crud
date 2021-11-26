package com.api.crud.repository;

import com.api.crud.entity.University;

import java.util.List;

public interface UniversityRepository {
    public University selectByID(Integer id);
    public University selectByName(String name);
    public List<University> selectAll();
    public int insert(University university);
    public int update(University university);
    public int delete(University university);

}
