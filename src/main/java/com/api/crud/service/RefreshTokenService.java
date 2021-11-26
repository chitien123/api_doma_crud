package com.api.crud.service;

import com.api.crud.entity.RefreshToken;

import java.util.List;

public interface RefreshTokenService {
    public RefreshToken findByToken(String token);
    public List<RefreshToken> findByUserId(Integer userId);
    public void verifyExpiration(RefreshToken refreshToken);
    public RefreshToken createRefreshToken(String username);
    public int insert(RefreshToken refreshToken);
    public int delete(RefreshToken refreshToken);
    public String selectTokenByUserId(Integer userId);
}
