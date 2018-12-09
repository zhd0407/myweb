package com.base.myweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWordConfiguration{

    @Bean
    SayHelloWorld sayHelloWorld(){
        System.out.println("helloworld configuration success");
        return new SayHelloWorld();
    }
}
