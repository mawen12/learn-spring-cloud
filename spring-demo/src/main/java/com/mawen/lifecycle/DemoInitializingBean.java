package com.mawen.lifecycle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DemoInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("触发：InitializingBean#afterPropertiesSet");
    }
}
