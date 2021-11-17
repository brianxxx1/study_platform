package com.wenshuo.eduService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wenshuo"})
public class eduApplicationStartUp {


    public static void main(String[] args) {
        SpringApplication.run(eduApplicationStartUp.class,args);
    }
}
