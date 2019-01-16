package com.demo.mapper;

import com.demo.domain.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("tMapper")
public interface TeacherMapper {
    @Select("select * from teacher")
    @Results({
            @Result(column = "t_name",property = "name"),
            @Result(column = "t_password",property = "password")
    })
    List<Teacher> findAll();

    @Select("Select * from teacher t where t.t_name = #{name}")
    @Results({
            @Result(column = "t_name",property = "name"),
            @Result(column = "t_password",property = "password")
    })
    Teacher login( String name);

    @Select("Select id,perms from teacher t where id = #{id}")
    Teacher perms( int id);

    @Insert("insert into teacher (t_name,t_password) values(#{name},#{password});")
    int add(Teacher teacher);

}
