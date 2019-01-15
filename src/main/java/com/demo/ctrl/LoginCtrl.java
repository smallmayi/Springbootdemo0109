package com.demo.ctrl;

import com.demo.service.Inter.TeacherInter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginCtrl {
   @Autowired
   TeacherInter tInter;
    /*@RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login( @RequestBody  Teacher teacher){
        String name = teacher.getName();
        String password = teacher.getPassword();
        System.out.println("name:"+name+"--"+"password:"+password);
        boolean flag = tInter.login(name,password);
        if (flag){
            System.out.println("success");
            return "login success";
        }
        return "info is error";
    }*/
    @RequestMapping("/login")
    public String login( String name,String password, Model model){
        System.out.println("name"+name);
        //使用Shrio编写认证操作
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法,有异常登录失败

        try {
            subject.login(token);
            return "redirect:/test";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("name","hello");
        return "test";
    }
    @RequestMapping("/add")
    public String add(Model model){

        return "/user/add";
    }
    @RequestMapping("/update")
    public String update(Model model){

        return "/user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(Model model){

        return "login";
    }

    //未授权页面
    @RequestMapping("/unAuth")
    public String unAuth(Model model){

        return "unauth";
    }
    //注册页面
    @RequestMapping("/register")
    public String register(){
        return "registerPage";
    }
    @RequestMapping("/doRegister")
    public String doRegister(String name,String password){
        tInter.register(name,password);
        return "login";
    }
}
