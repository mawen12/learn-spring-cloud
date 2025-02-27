package com.mawen.enhanded.zone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ZoneApp {

    public static void main(String[] args) {
        SpringApplication.run(ZoneApp.class, args);
    }
}
