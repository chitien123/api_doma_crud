package com.api.crud.service.impl;

import com.api.crud.controller.error.TokenRefreshException;
import com.api.crud.entity.RefreshToken;
import com.api.crud.entity.Users;
import com.api.crud.repository.RefreshTokenRepository;
import com.api.crud.repository.UsersRepository;
import com.api.crud.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public List<RefreshToken> findByUserId(Integer userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    @Override
    public int delete(RefreshToken refreshToken) {
        return refreshTokenRepository.delete(refreshToken);
    }

    @Override
    public String selectTokenByUserId(Integer userId) {
        return refreshTokenRepository.selectTokenByUserId(userId);
    }

    @Override
    public void verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(LocalDate.now()) < 0) {
            throw new TokenRefreshException(refreshToken.getToken(), "Expired token. Please issue a new request");
        }
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiryDate(LocalDate.now().plusDays(2));
        refreshToken.setToken(UUID.randomUUID().toString());
        Users user = usersRepository.selectByName(username);
        refreshToken.setUserId(user.id);
        return refreshToken;
    }

    @Override
    public int insert(RefreshToken refreshToken) {
        return refreshTokenRepository.insert(refreshToken);
    }

}
