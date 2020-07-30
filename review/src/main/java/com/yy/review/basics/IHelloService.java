package com.yy.review.basics;

public interface IHelloService {
    //接口默认的变量都是public static final的，不能有私有变量，必须final修饰
    public static final String s = "hello";
    String hello();
}
