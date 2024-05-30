package com.study.springstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.study.springstudy") //서블릿 공부할때 필요한거라 지금 필요없음
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}