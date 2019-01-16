package com.demo;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class test {

    public static void main(String[] args) {
       /* //盐值用的用的是对用户名的加密（测试用的"lisi"）
        ByteSource credentialsSalt01 = ByteSource.Util.bytes("张三");
        Object salt = null;//盐值
        Object credential = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        //1024指的是加密的次数
        Object simpleHash = new SimpleHash(hashAlgorithmName, credential,
                credentialsSalt01, 1024);
        System.out.println("加密后的值----->" + simpleHash);*/
        Object result = new SimpleHash("MD5","123456",ByteSource.Util.bytes("李四"),1024);
        System.out.println(result);
        char[] ch=new char[]{'字', '符', '串', '数', '组'};
        String c = new String(ch);
        String s = "字符串数组";
        System.out.println(c.equals(s));


    }
}
