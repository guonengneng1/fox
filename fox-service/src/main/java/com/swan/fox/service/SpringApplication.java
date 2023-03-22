package com.swan.fox.service;


import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication()
//@EnableDiscoveryClient //注册中心注解 使用nacos
//@NacosPropertySource(dataId = "product_config",autoRefreshed = true) //配置中心注解：autoRefreshed 代表自动刷新注解
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
//@NacosPropertySource(dataId = "fox-service",groupId = "Nacos:Test",autoRefreshed = true,type = ConfigType.JSON)


@NacosPropertySource(dataId = "fox-service",autoRefreshed = true)
public class SpringApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
}