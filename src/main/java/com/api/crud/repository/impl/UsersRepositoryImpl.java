package com.api.crud.repository.impl;

import com.api.crud.dao.UsersDao;
import com.api.crud.entity.Users;
import com.api.crud.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Users selectById(Integer id) {
        return usersDao.selectById(id);
    }

    @Override
    public Users selectByName(String name) {
        return usersDao.selectByName(name);
    }

    @Override
    public List<Users> selectAll() {
        return usersDao.selectAll();
    }

    @Override
    public int insert(Users users) {
        return usersDao.insert(users);
    }

    @Override
    public int update(Users users) {
        return usersDao.update(users);
    }

    @Override
    public int delete(Users users) {
        return usersDao.delete(users);
    }
}
