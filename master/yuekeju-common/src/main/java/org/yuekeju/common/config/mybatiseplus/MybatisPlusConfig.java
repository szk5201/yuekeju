package org.yuekeju.common.config.mybatiseplus;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 2020-7-10
 * @author szk
 * mybatise plus  分页
 */
@EnableTransactionManagement
@Configuration
@MapperScan("org.yuekeju.**.entity.**.*Entity")
/**
 * @author szk 扫描 Mapper 文件夹 【注：根据自己的项目结构配置】
 */
public class MybatisPlusConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	@Profile({"dev", "test"})
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		/*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
		performanceInterceptor.setMaxTime(1000);
		/*<!--SQL是否格式化 默认false-->*/
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}

}
