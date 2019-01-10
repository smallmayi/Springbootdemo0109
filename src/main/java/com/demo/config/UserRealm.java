package com.demo.config;

import com.demo.domain.Teacher;
import com.demo.service.Inter.TeacherInter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    TeacherInter tInter;
    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        //给资源执行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //从数据库获取授权字符串
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        Teacher principal = (Teacher) subject.getPrincipal();
        Teacher perms = tInter.perms(principal.getId());
        info.addStringPermission(perms.getPerms());
        return info;
    }
    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");

        //shiro判断逻辑
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Teacher login = tInter.login(token.getUsername());
        //System.out.println(login.getName()+"--"+login.getPassword());
        //if ((login == null)||!token.getUsername().equals(login.getName())){
        if ((login == null)){
            return null;
        }
        //principal授权时使用
       return new SimpleAuthenticationInfo(login,login.getPassword(),"");
    }
}
