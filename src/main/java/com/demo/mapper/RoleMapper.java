package com.demo.mapper;

import com.demo.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("rMapper")
public interface RoleMapper {
    @Select("select * from role where rid = #{rid}")
    List<Role> roleList(int rid);
}
