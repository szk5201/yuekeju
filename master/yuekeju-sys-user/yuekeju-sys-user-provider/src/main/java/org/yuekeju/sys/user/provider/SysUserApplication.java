package org.yuekeju.sys.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * 管理员用户服务
 *
 */
@SpringBootApplication
@ComponentScan("org.yuekeju")
@EnableFeignClients
public class SysUserApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(SysUserApplication.class, args);
    }
}
