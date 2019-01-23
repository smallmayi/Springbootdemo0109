package com.demo;

public class B implements A {
    public static void main(String[] args) {
        A a = new B();
        a.say();
    }
}
