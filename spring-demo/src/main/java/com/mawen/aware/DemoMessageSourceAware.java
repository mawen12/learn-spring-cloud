package com.mawen.aware;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

@Component
public class DemoMessageSourceAware implements MessageSourceAware {

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("触发：MessageSourceAware#setMessageSource, " + messageSource);
    }
}
