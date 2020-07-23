package org.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yy
 * @create 2020/7/23 13:50
 * @desc
 */
@SpringBootApplication
@ComponentScan(value = "com.yy.controller")
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
