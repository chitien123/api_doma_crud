package com.api.crud.repository.impl;

import com.api.crud.dao.StudentsDao;
import com.api.crud.entity.Students;
import com.api.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    StudentsDao studentsDao;


    @Override
    public Students selectById(Integer id) {
        return studentsDao.selectById(id);
    }

    @Override
    public Students selectByName(String name) {
        return studentsDao.selectByName(name);
    }

    @Override
    public List<Students> selectAll() {
        List<Students> studentsList = studentsDao.selectAll();

        return studentsList;
    }

    @Override
    public int insert(Students students) {
        return studentsDao.insert(students);
    }

    @Override
    public int update(Students students) {
        return studentsDao.update(students);
    }

    @Override
    public int delete(Students students) {
        return studentsDao.delete(students);
    }
}
