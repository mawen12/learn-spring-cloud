package com.mawen.enhanded.zone.controller;

import com.mawen.enhanded.zone.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zone")
public class ZoneController {

    @Value("${spring.cloud.nacos.discovery.metadata.zone}")
    private String zone;

    @Autowired
    private HelloClient helloClient;

    @GetMapping
    public String hello() {
        return "Hello " + zone;
    }

    @GetMapping("/say")
    public String say() {
        return "Say Hello";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return helloClient.sayHello();
    }
}
