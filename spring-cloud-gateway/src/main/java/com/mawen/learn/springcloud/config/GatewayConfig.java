package com.mawen.learn.springcloud.config;

import com.mawen.learn.springcloud.dynamic.CustomServiceInstanceListSupplierBuilder;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayConfig {

    @Bean
    public ServiceInstanceListSupplier zonePreferenceDiscoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
        return CustomServiceInstanceListSupplierBuilder.withZonePreference(context);
    }
}
