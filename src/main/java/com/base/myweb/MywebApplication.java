package com.base.myweb;

//import com.base.myweb.annotation.EnableHelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;*/

@SpringBootApplication
/*
@EnableEurekaClient
@EnableFeignClients
*/
@EnableSwagger2
public class MywebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MywebApplication.class, args);
    }
}
