package com.mawen.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class DemoBeanNameAware implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("触发：BeanNameAware#setBeanName, " + name);
    }
}