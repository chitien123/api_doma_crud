package com.api.crud.repository.impl;

import com.api.crud.dao.RefreshTokenDao;
import com.api.crud.entity.RefreshToken;
import com.api.crud.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    @Autowired
    RefreshTokenDao refreshTokenDao;

    @Override
    public RefreshToken findByToken(String token) {

        return refreshTokenDao.selectByToken(token);
    }

    @Override
    public List<RefreshToken> findByUserId(Integer userId) {

        return refreshTokenDao.selectByUserId(userId);
    }

    @Override
    public int insert(RefreshToken refreshToken) {
        return refreshTokenDao.insert(refreshToken);
    }

    @Override
    public int delete(RefreshToken refreshToken) {
        return refreshTokenDao.delete(refreshToken);
    }

    @Override
    public String selectTokenByUserId(Integer userId) {
        return refreshTokenDao.selectTokenByUserId(userId);
    }
}

