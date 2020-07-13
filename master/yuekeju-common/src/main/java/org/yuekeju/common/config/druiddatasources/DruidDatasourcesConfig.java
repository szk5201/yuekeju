package org.yuekeju.common.config.druiddatasources;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 2020-7-10
 * @author szk
 * 数据连接池
 */
@Configuration
@RefreshScope
@Data
@Slf4j
@ConditionalOnProperty(name = "myDatasources.enabled", havingValue = "true")
public class DruidDatasourcesConfig {
	@Value("${druid.login.enabled}")
    private boolean druidLoginEnabled;
 
    @Value("${druid.login.username}")
    private String druidLoginUsername;
 
    @Value("${druid.login.password}")
    private String druidLoginPassword;
 
    @Value("${spring.datasource.url}")
    private String dbUrl;
 
    @Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;
 
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.datasource.initial-size}")
    private int initialSize;
 
    @Value("${spring.datasource.min-idle}")
    private int minIdle;
 
    @Value("${spring.datasource.max-active}")
    private int maxActive;
 
    @Value("${spring.datasource.max-wait}")
    private int maxWait;
 
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
 
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
 
    @Value("${spring.datasource.validation-query}")
    private String validationQuery;
 
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
 
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
 
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
 
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
 
    @Value("${spring.datasource.filters}")
    private String filters;
 
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        //web访问是否需要登录
        if(druidLoginEnabled) {
            reg.addInitParameter("loginUsername", druidLoginUsername);
            reg.addInitParameter("loginPassword", druidLoginPassword);
        }
        return reg;
    }
 
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }
 
    @Bean
    @Primary
    public DataSource druidDataSource() {
    	log.info("开始配置druidDataSource");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
        	log.error("druid configuration initialization filter", e);
        }
        log.info("druidDataSource配置成功");
        return datasource;
    }
}
