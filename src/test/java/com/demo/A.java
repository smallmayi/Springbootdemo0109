package com.demo;

public interface A {
    public default void say(){
        System.out.println("hello");
    }
}
