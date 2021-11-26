package com.api.crud.service.impl;

import com.api.crud.entity.Users;
import com.api.crud.repository.UsersRepository;
import com.api.crud.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users selectById(Integer id) {
        return usersRepository.selectById(id);
    }

    @Override
    public Users selectByName(String name) {
        return usersRepository.selectByName(name);
    }

    @Override
    public List<Users> selectAll() {
        return usersRepository.selectAll();
    }

    @Override
    public int insert(Users users) {
        return usersRepository.insert(users);
    }

    @Override
    public int update(Users users) {
        return usersRepository.update(users);
    }

    @Override
    public int delete(Users users) {
        return usersRepository.delete(users);
    }
}
