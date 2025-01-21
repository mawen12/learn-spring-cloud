package com.mawen.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DemoEnvironmentAware implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("触发：EnvironmentAware#setEnvironment, " + environment);
    }
}
