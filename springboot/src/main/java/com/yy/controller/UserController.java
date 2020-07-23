package com.yy.controller;

import com.yy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(String userId) {
        return userService.getUser(userId);
    }
}
