package com.demo.service.Impl;

import com.demo.domain.Teacher;
import com.demo.mapper.TeacherMapper;
import com.demo.service.Inter.TeacherInter;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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

    @Override
    public void register(String name, String password) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        System.out.println(name + "---" + password);
        String md5 = new SimpleHash("MD5", password, ByteSource.Util.bytes(name), 1024).toHex();
        teacher.setPassword(md5);
        tMapper.add(teacher);
    }
}