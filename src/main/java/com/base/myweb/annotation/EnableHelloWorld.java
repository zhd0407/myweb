package com.base.myweb.annotation;


import com.base.myweb.configuration.HelloWordConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({HelloWordConfiguration.class})
public @interface EnableHelloWorld {

}
