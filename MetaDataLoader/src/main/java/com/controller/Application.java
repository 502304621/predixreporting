package com.controller;
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.context.annotation.Configuration; 
 
/*@EnableAutoConfiguration 
@Configuration 
@ComponentScan*/
@SpringBootApplication
public class Application extends SpringBootServletInitializer { 
 
    public static void main(String[] args) throws Throwable { 
        SpringApplication app = new SpringApplication(Application.class); 
        app.setShowBanner(false); 
        app.run(args); 
    } 
} 