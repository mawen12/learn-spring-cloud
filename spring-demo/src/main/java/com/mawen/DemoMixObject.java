package com.mawen;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Component;

@Component
public class DemoMixObject implements
        ApplicationContextAware,
        ApplicationEventPublisherAware,
        ApplicationStartupAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        BeanNameAware,
        EnvironmentAware,
        ImportAware,
        LoadTimeWeaverAware,
        MessageSourceAware,
        ResourceLoaderAware,
        InitializingBean,
        DisposableBean,
        BeanFactoryPostProcessor,
        BeanPostProcessor {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("触发：BeanClassLoaderAware#setBeanClassLoader");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("触发：BeanFactoryAware#setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("触发：BeanNameAware#setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("触发：DisposableBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("触发：InitializingBean#destroy");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("触发：BeanFactoryPostProcessor#postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("触发：ApplicationContextAware#setApplicationContext");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("触发：ApplicationEventPublisherAware#setApplicationEventPublisher");
    }

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        System.out.println("触发：ApplicationStartupAware#setApplicationStartup");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("触发：EnvironmentAware#setEnvironment");
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        System.out.println("触发：ImportAware#setImportMetadata");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("触发：MessageSourceAware#setMessageSource");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("触发：ResourceLoaderAware#setResourceLoader");

    }

    @Override
    public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {
        System.out.println("触发：LoadTimeWeaverAware#setLoadTimeWeaver");
    }
}
