package com.api.crud.service.impl;

import com.api.crud.entity.Students;
import com.api.crud.repository.StudentRepository;
import com.api.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Students selectById(Integer id) {
        return studentRepository.selectById(id);
    }

    @Override
    public Students selectByName(String name) {
        return studentRepository.selectByName(name);
    }

    @Override
    public List<Students> selectAll() {
        return studentRepository.selectAll();
    }

    @Override
    public int insert(Students students) {
        return studentRepository.insert(students);
    }

    @Override
    public int update(Students students) {
        return studentRepository.update(students);
    }

    @Override
    public int delete(Students students) {
        return studentRepository.delete(students);
    }
}
