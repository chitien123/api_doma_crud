package com.api.crud.repository.impl;

import com.api.crud.dao.UniversityDao;
import com.api.crud.entity.University;
import com.api.crud.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UniversityRepositoryImpl implements UniversityRepository {
    @Autowired
    UniversityDao universityDao;
    @Override
    public University selectByID(Integer id) {

        return universityDao.selectById(id);
    }

    @Override
    public University selectByName(String name) {
        return universityDao.selectByName(name);
    }

    @Override
    public List<University> selectAll() {

        return universityDao.selectAll();
    }

    @Override
    public int insert(University university) {

       return universityDao.insert(university);
    }

    @Override
    public int update(University university) {

        return universityDao.update(university);
    }

    @Override
    public int delete(University university) {

        return universityDao.delete(university);
    }
}
