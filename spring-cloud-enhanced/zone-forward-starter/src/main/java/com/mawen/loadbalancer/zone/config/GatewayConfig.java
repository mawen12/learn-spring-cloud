package com.mawen.loadbalancer.zone.config;


import com.mawen.loadbalancer.zone.dynamic.CustomServiceInstanceListSupplierBuilder;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@ConditionalOnDiscoveryEnabled
public class GatewayConfig {

    @Bean
    public ServiceInstanceListSupplier zonePreferenceDiscoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
        return CustomServiceInstanceListSupplierBuilder.withZonePreference(context);
    }
}
