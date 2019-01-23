package com.demo;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import sun.plugin2.message.Message;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class test {
    private static final String[] digtal = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       /* //盐值用的用的是对用户名的加密（测试用的"lisi"）
        ByteSource credentialsSalt01 = ByteSource.Util.bytes("张三");
        Object salt = null;//盐值
        Object credential = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        //1024指的是加密的次数
        Object simpleHash = new SimpleHash(hashAlgorithmName, credential,
                credentialsSalt01, 1024);
        System.out.println("加密后的值----->" + simpleHash);*/
       /* Object result = new SimpleHash("MD5","123456",ByteSource.Util.bytes("李四"),1024);
        System.out.println(result);
        char[] ch=new char[]{'字', '符', '串', '数', '组'};
        String c = new String(ch);
        String s = "字符串数组";
        System.out.println(c.equals(s));*/

    }

    private static String initMD5(String txt) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //String txt = "123456";
        byte[] bytes = md5.digest(txt.getBytes("utf-8"));
        //定义一个空字符串，用来接收最终密文
        String enctry = "";
        for (byte b : bytes){
            //每次循环时从digital数组取出2个同字符--》随即取出0-15的两个下标
            int temp = b;
            if (temp < 0){
                temp = temp + 256;
            }
            //第一个下标
            int i = temp / 16;
            //第二个下标
            int j = temp % 16;
            enctry += digtal[i] + digtal[j];
        }
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);
        System.out.println(enctry);
        return enctry;
    }
    public static String finishMD5(String txt)throws Exception{
        String md5 = initMD5(initMD5(initMD5(txt)+"baidu"));
        return md5;
    }
}
