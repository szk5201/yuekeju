package org.yuekeju.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关服务
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("org.yuekeju")
@EnableFeignClients
public class YuekejuGatewayApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(YuekejuGatewayApplication.class, args);
    }
}
