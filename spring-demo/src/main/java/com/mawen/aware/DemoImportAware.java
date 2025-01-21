package com.mawen.aware;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

@Component
public class DemoImportAware implements ImportAware {

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        System.out.println("触发：ImportAware#setImportMetadata, " + importMetadata);
    }
}
