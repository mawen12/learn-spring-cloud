package com.mawen.aware;

import org.springframework.context.ApplicationStartupAware;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationStartupAware implements ApplicationStartupAware {

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        System.out.println("触发：ApplicationStartupAware#setApplicationStartup, " + applicationStartup);
    }
}
