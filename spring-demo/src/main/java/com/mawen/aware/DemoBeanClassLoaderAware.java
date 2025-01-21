package com.mawen.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;

@Component
public class DemoBeanClassLoaderAware implements BeanClassLoaderAware {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("触发：BeanClassLoaderAware#setBeanClassLoader, " + classLoader);
    }
}
