package com.mawen.learn.springcloud.feigncontroller.annotation;


import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignController {

    String value();
}
