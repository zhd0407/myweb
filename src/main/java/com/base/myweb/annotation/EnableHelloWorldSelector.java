package com.base.myweb.annotation;


import com.base.myweb.configuration.HelloWorldConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({HelloWorldConfigurationSelector.class})
public @interface EnableHelloWorldSelector {

    String model() default "first";

}
