package com.api.crud.service.impl;

import com.api.crud.entity.University;
import com.api.crud.repository.UniversityRepository;
import com.api.crud.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    UniversityRepository universityRepository;

    @Override
    public University selectById(Integer id) {
        return universityRepository.selectByID(id);
    }

    @Override
    public University selectByName(String name) {
        return universityRepository.selectByName(name);
    }

    @Override
    public List<University> selectAll() {
        return universityRepository.selectAll();
    }

    @Override
    public int insert(University university) {
        return universityRepository.insert(university);
    }

    @Override
    public int update(University university) {
        return universityRepository.update(university);
    }

    @Override
    public int delete(University university) {
        return universityRepository.delete(university);
    }
}
