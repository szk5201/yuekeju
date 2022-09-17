package org.yuekeju.sys.user.provider;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 2020-7-10
 * @author szk
 * 管理员用户服务
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@ComponentScan("org.yuekeju")
@EnableFeignClients("org.yuekeju.sys.user.provider.controller")
@EnableDiscoveryClient
// @EnableCaching  /** @author szk 开启缓存*/
public class YuekejuSysUserProviderApplication
{
    public static void main( String[] args )
    {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(YuekejuSysUserProviderApplication.class, args);
    }
}
