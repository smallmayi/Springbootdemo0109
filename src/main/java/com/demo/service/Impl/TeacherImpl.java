package com.demo.service.Impl;

import com.demo.domain.Teacher;
import com.demo.mapper.TeacherMapper;
import com.demo.service.Inter.TeacherInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherImpl implements TeacherInter {
    @Autowired
    TeacherMapper tMapper;

    @Override
    public List<Teacher> findAll() {
        return tMapper.findAll();
    }

    @Override
    public Teacher login(String name) {
        Teacher teacher = tMapper.login(name);
        return teacher;

    }

    @Override
    public Teacher perms(int id) {
        Teacher teacher = tMapper.perms(id);
        return teacher;
    }
}