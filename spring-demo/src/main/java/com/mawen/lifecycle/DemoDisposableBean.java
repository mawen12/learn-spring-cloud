package com.mawen.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class DemoDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("触发：DisposableBean#destroy");
    }
}
