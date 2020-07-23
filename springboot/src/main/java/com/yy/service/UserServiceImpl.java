package com.yy.service;

import org.springframework.stereotype.Service;

/**
 * @author yy
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public String getUser(String userId) {
        System.out.println(userId);
        return userId;
    }

}
