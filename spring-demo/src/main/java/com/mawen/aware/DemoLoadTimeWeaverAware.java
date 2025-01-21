package com.mawen.aware;

import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Component;

@Component
public class DemoLoadTimeWeaverAware implements LoadTimeWeaverAware {

    @Override
    public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {
        System.out.println("触发：LoadTimeWeaverAware#setLoadTimeWeaver, " + loadTimeWeaver);
    }
}
