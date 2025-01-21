package com.mawen.learn.springcloud.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.mawen.learn.springcloud.post")
public class PostApp {

    public static void main(String[] args) {
        SpringApplication.run(PostApp.class, args);
    }
}
