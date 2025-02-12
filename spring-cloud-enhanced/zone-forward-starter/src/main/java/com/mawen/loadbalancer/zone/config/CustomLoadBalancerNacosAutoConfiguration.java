package com.mawen.loadbalancer.zone.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@ConditionalOnDiscoveryEnabled
@LoadBalancerClients(defaultConfiguration = {GatewayConfig.class})
public class CustomLoadBalancerNacosAutoConfiguration {
}
