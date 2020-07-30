package com.yy.spring.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {

    public String hello() {
        return "Hello";
    }
}
