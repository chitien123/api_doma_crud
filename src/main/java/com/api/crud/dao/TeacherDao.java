package com.api.crud.dao;

import com.api.crud.entity.Teachers;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface TeacherDao {

    @Sql("select  /*%expand*/* from TEACHER where ID = /* id */0 ")
    @Select
    Teachers selectById(Integer id);

    @Sql("select * from TEACHER where /*%if name != null*/FullName = /*name*/'ccc' /*%end*/")
    @Select
    Teachers selectByName(String name);

    @Sql("select  /*%expand*/* from TEACHER")
    @Select
    List<Teachers> selectAll();

    @Insert
    int insert(Teachers teachers);

    @Update
    int update(Teachers teachers);

    @Delete
    int delete(Teachers teachers);
}
