package com.mawen.learn.nacos;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.builder.InstanceBuilder;

import java.util.Properties;

public class NamingServiceDemo {

    public static void main(String[] args) throws NacosException, InterruptedException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "localhost:8848");

        NamingService namingService = NamingFactory.createNamingService(properties);

        namingService.registerInstance("demo", InstanceBuilder.newBuilder()
                        .setIp("127.0.0.1")
                        .setPort(8081)
                        .setWeight(1.0)
                        .setClusterName("demo")
                        .addMetadata("zone", "mawen")
                .build());

        Thread.sleep(1000L);

        namingService.shutDown();
    }
}
