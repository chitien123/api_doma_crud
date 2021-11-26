package com.api.crud.service;

import com.api.crud.entity.Users;

import java.util.List;

public interface UsersService {
    public Users selectById(Integer id);
    public Users selectByName(String name);
    public List<Users> selectAll();
    public int insert(Users users);
    public int update(Users users);
    public int delete(Users users);
}
