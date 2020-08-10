package com.yy.spring.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {

    public void setName(String name) {
        this.name = name;
    }

    public String name;
    public String hello() {
        return "Hello";
    }

}
