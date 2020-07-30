package com.yy.spring.controller;

import com.yy.spring.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return helloService.hello();
    }
}
