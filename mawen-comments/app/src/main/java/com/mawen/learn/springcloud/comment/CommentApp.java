package com.mawen.learn.springcloud.comment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CommentApp {

    public static void main(String[] args) {
        SpringApplication.run(CommentApp.class, args);
    }
}
