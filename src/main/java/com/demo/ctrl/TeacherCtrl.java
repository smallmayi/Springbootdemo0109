package com.demo.ctrl;

import com.demo.domain.Teacher;
import com.demo.service.Inter.TeacherInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherCtrl {
    @Autowired
    TeacherInter tInter;

    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public List<Teacher> findAll(){

        return tInter.findAll();
    }

}
