package com.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /*Shiro内置过滤器，实现权限相关的拦截器
        *常用过滤器：
        *   anon:无需认证可以访问
        *   authc:必须认证才可以访问
        *   user:如果使用rememberMe功能可以直接访问
        *   perms:该资源必须得到资源权限才可以访问
        *   role:给资源必须得到角色权限才可以访问
        *
        * */
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/
        filterMap.put("/test","anon");
        filterMap.put("/login","anon");

        //授权过滤器
        //当前授权拦截和，shiro自动跳转到未授权页面
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");
        filterMap.put("/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //默认跳转到login.jsp，修改为toLogin
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");


        return shiroFilterFactoryBean;
    }
    //创建DefaultWebSecurityManager
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建Realm
    @Bean("userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    //配置ShiroDialect,用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);//是否16进制
        return hashedCredentialsMatcher;
    }

/*    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //告诉realm,使用credentialsMatcher加密算法类来验证密文
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCachingEnabled(false);
        return userRealm;
    }*/
    /**
     * 身份认证 realm
     */
    @Bean("myShiroRealm")
    public UserRealm myShiroRealm(){
        UserRealm myShiroRealm = new UserRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        System.out.println("myShiroRealm 注入成功");
        return myShiroRealm;
    }

    @Bean
    public DefaultSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 注入自定义的realm;
        securityManager.setRealm(myShiroRealm());
        System.out.println("注入securtyManager");
        // 注入缓存管理器;
        //securityManager.setCacheManager(ehCacheManager());

        return securityManager;
    }



}
