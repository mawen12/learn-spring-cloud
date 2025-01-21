package com.mawen.aware;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationEventPublisherAware implements ApplicationEventPublisherAware {

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("触发：ApplicationEventPublisherAware#setApplicationEventPublisher, " + applicationEventPublisher);
    }
}
