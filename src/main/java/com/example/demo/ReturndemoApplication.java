package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class ReturndemoApplication extends SpringBootServletInitializer {
    public class ReturndemoApplication{

    //war
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return  super.configure(builder);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ReturndemoApplication.class, args);
    }

}
