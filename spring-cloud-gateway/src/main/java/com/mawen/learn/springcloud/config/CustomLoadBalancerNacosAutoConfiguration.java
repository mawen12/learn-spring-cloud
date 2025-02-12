package com.mawen.learn.springcloud.config;

import com.alibaba.cloud.nacos.ConditionalOnNacosDiscoveryEnabled;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@ConditionalOnNacosDiscoveryEnabled
@LoadBalancerClients(defaultConfiguration = {GatewayConfig.class})
public class CustomLoadBalancerNacosAutoConfiguration {
}
