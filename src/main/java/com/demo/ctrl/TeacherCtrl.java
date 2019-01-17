package com.demo.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.demo.service.Inter.TeacherInter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TeacherCtrl {
    @Autowired
    TeacherInter tInter;

    @RequestMapping("/loginin")
    public JSONObject login(String name, String password) {
        Subject subject = SecurityUtils.getSubject();
        JSONObject json = new JSONObject();
        Session session = subject.getSession();
        String sessionId = (String) session.getId();
        json.put("sessionId", sessionId);

        return json;


    }
}
