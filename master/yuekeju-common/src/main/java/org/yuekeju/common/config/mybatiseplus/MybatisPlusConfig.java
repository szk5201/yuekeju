package org.yuekeju.common.config.mybatiseplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * 2020-7-10
 * @author szk
 * mybatise plus  分页
 */
@EnableTransactionManagement
@Configuration
@MapperScan("org.yuekeju.**.entity.**.*Entity") /**@author szk 扫描 Mapper 文件夹 【注：根据自己的项目结构配置】*/
public class MybatisPlusConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
