package com.mawen.learn.springcloud.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AdditionalRoutes {

    @Value("${test.uri:http://httpbin.org:80}")
    String uri = null;
}
