package com.api.crud.dao;


import com.api.crud.entity.Users;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UsersDao {
    @Sql("select  /*%expand*/* from USERS where ID = /* id */99 ")
    @Select
    Users selectById(Integer id);

    @Sql("select * from USERS where /*%if name != null*/UserName = /*name*/'ccc' /*%end*/")
    @Select
    Users selectByName(String name);

    @Sql("select  /*%expand*/* from USERS")
    @Select
    List<Users> selectAll();

    @Insert
    int insert(Users students);

    @Update
    int update(Users students);

    @Delete
    int delete(Users students);
}
