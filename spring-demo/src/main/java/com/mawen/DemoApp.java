package com.mawen;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DemoApp.class);
        context.register(DemoMixObject.class);

        System.out.println("Context before refresh");

        context.refresh();

        System.out.println("Context already refresh");

        context.close();

        System.out.println("Context close refresh");
    }
}
