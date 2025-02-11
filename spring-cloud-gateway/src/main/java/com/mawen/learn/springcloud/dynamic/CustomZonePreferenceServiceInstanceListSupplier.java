package com.mawen.learn.springcloud.dynamic;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultRequest;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestDataContext;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.loadbalancer.config.LoadBalancerZoneConfig;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ZonePreferenceServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomZonePreferenceServiceInstanceListSupplier extends ZonePreferenceServiceInstanceListSupplier {

    private boolean callGetWithRequestOnDelegates;

    public CustomZonePreferenceServiceInstanceListSupplier(ServiceInstanceListSupplier delegate, LoadBalancerZoneConfig zoneConfig) {
        super(delegate, zoneConfig);
    }

    public CustomZonePreferenceServiceInstanceListSupplier(ServiceInstanceListSupplier delegate, LoadBalancerZoneConfig zoneConfig, ReactiveLoadBalancer.Factory<ServiceInstance> loadBalancerClientFactory) {
        super(delegate, zoneConfig, loadBalancerClientFactory);
        callGetWithRequestOnDelegates = loadBalancerClientFactory.getProperties(getServiceId())
                .isCallGetWithRequestOnDelegates();
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        if (callGetWithRequestOnDelegates) {
            final String zoneFromHeader = getZoneFromHeader(request);
            return getDelegate().get(request).map(instances -> filteredByCustomZone(zoneFromHeader, instances));
        }
        return get();
    }

    private List<ServiceInstance> filteredByCustomZone(String zone, List<ServiceInstance> serviceInstances) {
        if (zone != null) {
            List<ServiceInstance> filteredInstances = new ArrayList<>();
            for (ServiceInstance serviceInstance : serviceInstances) {
                String instanceZone = getZone(serviceInstance);
                if (zone.equalsIgnoreCase(instanceZone)) {
                    filteredInstances.add(serviceInstance);
                }
            }
            if (filteredInstances.size() > 0) {
                return filteredInstances;
            }
        }
        // If the zone is not set or there are no zone-specific instances available,
        // we return all instances retrieved for given service id.
        return serviceInstances;
    }

    private String getZone(ServiceInstance serviceInstance) {
        Map<String, String> metadata = serviceInstance.getMetadata();
        if (metadata != null) {
            return metadata.get("zone");
        }
        return null;
    }

    private String getZoneFromHeader(Request request) {
        if (request instanceof DefaultRequest) {
            DefaultRequest<RequestDataContext> defaultRequest = (DefaultRequest<RequestDataContext>) request;
            HttpHeaders headers = defaultRequest.getContext().getClientRequest().getHeaders();
            return headers.getFirst("zone");
        }
        return null;
    }
}
