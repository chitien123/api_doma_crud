package com.api.crud.repository;

import com.api.crud.entity.Users;

import java.util.List;

public interface UsersRepository {
    public Users selectById(Integer id);
    Users selectByName(String name);
    public List<Users> selectAll();
    public int insert(Users users);
    public int update(Users users);
    public int delete(Users users);
}
