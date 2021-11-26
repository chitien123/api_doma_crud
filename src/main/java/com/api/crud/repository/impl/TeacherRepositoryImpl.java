package com.api.crud.repository.impl;

import com.api.crud.dao.TeacherDao;
import com.api.crud.entity.Teachers;
import com.api.crud.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
@Autowired
TeacherDao teacherDao;

    @Override
    public Teachers selectById(Integer id) {
        return teacherDao.selectById(id);
    }

    @Override
    public Teachers selectByName(String name) {
        return teacherDao.selectByName(name);
    }

    @Override
    public List<Teachers> selectAll() {
        return teacherDao.selectAll();
    }

    @Override
    public int insert(Teachers teachers) {
        return teacherDao.insert(teachers);
    }

    @Override
    public int update(Teachers teachers) {
        return teacherDao.update(teachers);

    }

    @Override
    public int delete(Teachers teachers) {
        return teacherDao.delete(teachers);

    }
}
