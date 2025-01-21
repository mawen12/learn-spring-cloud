package com.mawen.aware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class DemoResourceLoaderAware implements ResourceLoaderAware {

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("触发：ResourceLoaderAware#setResourceLoader, " + resourceLoader);
    }
}
