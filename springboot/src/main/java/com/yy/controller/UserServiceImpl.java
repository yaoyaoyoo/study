package com.yy.controller;

import com.yy.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yy
 */
@RestController
public class UserServiceImpl implements IUserService {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    @Override
    public String getUser(String userId) {
        System.out.println(userId);
        return userId;
    }

}
