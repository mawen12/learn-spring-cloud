package com.mawen.enhanded.zone.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "hello", path = "/api/hello")
public interface HelloClient {

    @GetMapping
    public String sayHello();
}
