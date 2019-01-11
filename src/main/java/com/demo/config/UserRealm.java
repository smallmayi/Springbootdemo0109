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
import org.apache.shiro.util.ByteSource;
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
        String tname = token.getUsername();
        String tpassword =new String(token.getPassword());
        System.out.println("tname:"+tname+"tpass"+tpassword);
        Teacher login = tInter.login(token.getUsername());
        System.out.println(login.toString());
        if ((login == null)){
            return null;
        }
        //principal授权时使用
      // return new SimpleAuthenticationInfo(login,login.getPassword(),"");
        // 认证的实体信息，可以是username，也可以是用户的实体类对象，这里用的用户名
        //Object principal = token.getUsername();
        //System.out.println(token.getUsername()+"----"+token.getPassword());
        // 从数据库中查询的密码
        //Object credentials = login.getPassword();
        System.out.println("数据库密码："+login.getPassword());
        // 颜值加密的颜，可以用用户名
        ByteSource salt = ByteSource.Util.bytes(token.getUsername());
        // 当前realm对象的名称，调用分类的getName()
        String realmName = getName();
        System.out.println(realmName.toString());
        // 创建SimpleAuthenticationInfo对象，并且把username和password等信息封装到里面
        // 用户密码的比对是Shiro帮我们完成的
        SimpleAuthenticationInfo info = null;
       // info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
       info = new SimpleAuthenticationInfo(login, login.getPassword(),salt, realmName);

        return info;


    }

}
