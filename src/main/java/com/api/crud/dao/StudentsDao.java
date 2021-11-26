package com.api.crud.dao;
import com.api.crud.entity.Students;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface StudentsDao {
    @Sql("select  /*%expand*/* from STUDENTS where ID = /* id */99 ")
    @Select
    Students selectById(Integer id);

    @Sql("select * from STUDENTS where /*%if name != null*/FullName = /*name*/'ccc' /*%end*/")
    @Select
    Students selectByName(String name);

    @Sql("select  /*%expand*/* from STUDENTS")
    @Select
    List<Students> selectAll();

    @Insert
    int insert(Students students);

    @Update
    int update(Students students);

    @Delete
    int delete(Students students);

}
