package org.yuekeju.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户服务
 * szk  2020-7-3
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("org.yuekeju")
@EnableFeignClients
public class YuekejuUserApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(YuekejuUserApplication.class, args);
    }
}
