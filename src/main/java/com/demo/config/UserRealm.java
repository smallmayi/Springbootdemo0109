package com.demo.config;

import com.demo.domain.Teacher;
import com.demo.service.Inter.TeacherInter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
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
        //Object principal = subject.getPrincipal();
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
        String tpassword = String.valueOf(token.getPassword());
        Teacher login = tInter.login(tname);
        if (login == null) {
            return null;
        } else {
            //String credentials = new Sha512Hash(tpassword, tname).toString();
            String md5 = new SimpleHash("MD5", tpassword, ByteSource.Util.bytes(tname), 1024).toHex();
            token.setPassword(md5.toCharArray());
            System.out.println("cred:"+md5);
           // return new SimpleAuthenticationInfo(tname,tpassword, getName());
            SimpleAuthenticationInfo info = null;
            // info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
            String realname = getName();
            // 颜值加密的颜，可以用用户名
            ByteSource salt = ByteSource.Util.bytes(tname);
            info = new SimpleAuthenticationInfo(login,login.getPassword(),salt, realname);
            return info;
        }


    }

}
