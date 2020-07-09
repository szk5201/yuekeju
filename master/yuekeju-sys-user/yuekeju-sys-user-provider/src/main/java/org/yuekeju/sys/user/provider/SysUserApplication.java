package org.yuekeju.sys.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * 管理员用户服务
 *
 */
@SpringBootApplication
@ComponentScan("org.yuekeju")
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching // 开启缓存
@EnableTransactionManagement // 开启事务，保证redis与mysql中数据的一致性
public class SysUserApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(SysUserApplication.class, args);
    }
}
