package com.api.crud.service.impl;

import com.api.crud.entity.Teachers;
import com.api.crud.repository.TeacherRepository;
import com.api.crud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Teachers selectById(Integer id) {
        return teacherRepository.selectById(id);
    }

    @Override
    public Teachers selectByName(String name) {
        return teacherRepository.selectByName(name);
    }

    @Override
    public List<Teachers> selectAll() {
        return teacherRepository.selectAll();
    }

    @Override
    public int insert(Teachers teachers) {
        return teacherRepository.insert(teachers);
    }

    @Override
    public int update(Teachers teachers) {
        return teacherRepository.update(teachers);
    }

    @Override
    public int delete(Teachers teachers) {
        return teacherRepository.delete(teachers);
    }
}
