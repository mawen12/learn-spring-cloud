package com.mawen.enhanded.zone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zone")
public class HelloController {

    @Value("${spring.cloud.nacos.discovery.metadata.zone}")
    private String zone;

    @GetMapping
    public String hello() {
        return "Hello " + zone;
    }

    @GetMapping("/say")
    public String say() {
        return "Say Hello";
    }
}
