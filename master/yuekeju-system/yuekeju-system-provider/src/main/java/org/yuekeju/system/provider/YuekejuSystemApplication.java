package org.yuekeju.system.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 2020-07-10
 * @author szk
 * 系统配置管理服务
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class YuekejuSystemApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(YuekejuSystemApplication.class, args);
    }
}
