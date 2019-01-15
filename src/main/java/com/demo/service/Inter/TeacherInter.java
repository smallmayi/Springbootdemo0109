package com.demo.service.Inter;

import com.demo.domain.Teacher;

import java.util.List;

public interface TeacherInter {
    List<Teacher> findAll();
    Teacher login(String name);
    Teacher perms(int id);
    void register(String name,String password);
}
