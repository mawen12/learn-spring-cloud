package com.mawen.learn.springcloud.dynamic;

import org.springframework.cloud.loadbalancer.config.LoadBalancerZoneConfig;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplierBuilder;
import org.springframework.cloud.loadbalancer.core.ZonePreferenceServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomServiceInstanceListSupplierBuilder  {

    public static ServiceInstanceListSupplier withZonePreference(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
                .withCaching()
                .with(withZonePreference("remote"))
                .build(context);
    }

    public static ServiceInstanceListSupplierBuilder.DelegateCreator withZonePreference(String zoneName) {
        return (context, delegate) -> {
            LoadBalancerClientFactory loadBalancerClientFactory = context.getBean(LoadBalancerClientFactory.class);
            LoadBalancerZoneConfig zoneConfig = new LoadBalancerZoneConfig(zoneName);
            return new CustomZonePreferenceServiceInstanceListSupplier(delegate, zoneConfig, loadBalancerClientFactory);
        };
    }
}
