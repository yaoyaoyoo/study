package com.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yy
 * @create 2020/7/23 13:50
 * @desc
 */

/**
 * ComponentScan(value = "com.yy")
 * SpringBoot在没配置@ComponentScan的情况下，默认只扫描和主类处于同包下的Class。
 * */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
