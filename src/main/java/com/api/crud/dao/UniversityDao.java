package com.api.crud.dao;

import com.api.crud.entity.University;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UniversityDao {

    @Sql("select  /*%expand*/* from University where ID = /* id */1")
    @Select
    University selectById(Integer id);

    @Sql("select * from University where /*%if name != null*/FullName = /*name*/'ccc' /*%end*/")
    @Select
    University selectByName(String name);

    @Sql("select  /*%expand*/* from University")
    @Select
    List<University> selectAll();

    @Insert
    int insert(University university);

    @Update
    int update(University university);

    @Delete
    int delete(University university);
}
