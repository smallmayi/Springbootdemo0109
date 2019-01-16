package com.demo.mapper;

import com.demo.domain.Perms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("pMapper")
public interface PermsMapper {
    @Select("select * from perms where rid = #{rid}")
    List<Perms> permList(int rid);
}
