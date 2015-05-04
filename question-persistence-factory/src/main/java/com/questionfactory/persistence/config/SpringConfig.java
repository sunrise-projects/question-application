package com.questionfactory.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.questionfactory.persistence.service.Hello;
import com.questionfactory.persistence.service.impl.HelloImpl;


@Configuration
public class SpringConfig {
 
    @Bean(name="helloBean")
    public Hello helloWorld() {
        return new HelloImpl();
    }
      
 
}