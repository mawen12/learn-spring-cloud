package com.mawen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Value("${spring.cloud.nacos.discovery.metadata.zone}")
    private String zone;

    @GetMapping
    public String sayHello() {
        return zone + "From Hello";
    }
}
