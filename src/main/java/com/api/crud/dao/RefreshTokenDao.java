package com.api.crud.dao;


import com.api.crud.entity.RefreshToken;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface RefreshTokenDao {
    @Sql("select  /*%expand*/* from refresh_token where Token = /* token */0 ")
    @Select
    RefreshToken selectByToken(String token);

    @Sql("select Token from refresh_token where UserId = /* userId */0")
    @Select
    String selectTokenByUserId(Integer userId);

    @Sql("select  /*%expand*/* from refresh_token where UserId = /* userId */0 ")
    @Select
    List<RefreshToken> selectByUserId(Integer userId);


    @Insert
    int insert(RefreshToken refreshToken);

    @Delete
    int delete(RefreshToken refreshToken);
}