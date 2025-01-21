package com.mawen.learn.springcloud.post.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/config")
public class ConfigController {

    @Value("${post.name}")
    private String name;

    @GetMapping("")
    public String getName() {
        return name;
    }
}
