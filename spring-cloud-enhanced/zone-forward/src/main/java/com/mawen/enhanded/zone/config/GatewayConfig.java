package com.mawen.enhanded.zone.config;

import com.mawen.enhanded.zone.dynamic.CustomServiceInstanceListSupplierBuilder;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@ConditionalOnDiscoveryEnabled
public class GatewayConfig {

//    @Bean
//    public LoadBalancerAlgorithm nacosLoadBalancer(Environment environment) {
//        System.out.println(environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME));
//        String name = "zone";
//        return new ZoneAwareLoadBalancerAlgorithm(name);
//    }

    @Bean
    public ServiceInstanceListSupplier zonePreferenceDiscoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
        return CustomServiceInstanceListSupplierBuilder.withZonePreference(context);
    }
}
