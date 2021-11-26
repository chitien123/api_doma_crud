package com.api.crud.repository;

import com.api.crud.entity.RefreshToken;

import java.util.List;

public interface RefreshTokenRepository {
    public RefreshToken findByToken(String token);
    public List<RefreshToken> findByUserId(Integer userId);
    public int insert(RefreshToken refreshToken);
    public int delete(RefreshToken refreshToken);
    public String selectTokenByUserId(Integer userId);
}
