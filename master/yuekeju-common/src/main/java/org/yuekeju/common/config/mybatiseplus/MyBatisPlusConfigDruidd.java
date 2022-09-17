package org.yuekeju.common.config.mybatiseplus;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.toolkit.GlobalConfigUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableTransactionManagement
@ConditionalOnProperty(name = "myDatasources.enabled", havingValue = "true")
@Configuration
@MapperScan(basePackages = "com.yuekeju")
public class MyBatisPlusConfigDruidd {


    @Value("${spring.datasource.master.url}")
    private String masterUrl;

    @Value("${spring.datasource.master.username}")
    private String masterUserName;

    @Value("${spring.datasource.master.password}")
    private String masterPassword;

    @Value("${spring.datasource.slave.url}")
    private String slaveUrl;

    @Value("${spring.datasource.slave.username}")
    private String slaveUserName;

    @Value("${spring.datasource.slave.password}")
    private String slavePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    /**
     * time for connection wait
     */
    @Value("${spring.datasource.max-active}")
    private int maxActive;

    @Value("${spring.datasource.max-wait}")
    private int maxWait;

    @Value("${spring.datasource.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.max-pool-prepared-statement-per-connection-size}")
    private int maxOpenPreparedStatementConnectionSize;

    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.test-on-return}")
    private boolean testOnReturn;

    @Value("${spring.datasource.pool-prepared-statements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Value("${spring.datasource.filters}")
    private String filters;


    @Bean(name = "master")
    public DataSource master() throws SQLException {
        return getDruidDataSource(masterUrl, masterUserName, masterPassword);
    }

    @Bean(name = "slave")
    public DataSource slave() throws SQLException {
        return getDruidDataSource(slaveUrl, slaveUserName, slavePassword);
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        //return new META;
        return new MetaObjectHandlerConfig();
    }
    private DruidDataSource getDruidDataSource(String url, String userName, String password) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatementConnectionSize);
        druidDataSource.setConnectionProperties(connectionProperties);
        druidDataSource.setFilters(filters);
        List<Filter> filters = new ArrayList<>();
        filters.add(createWallFilter());
        druidDataSource.setProxyFilters(filters);
        return druidDataSource;
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("master") DataSource master,
                                               @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DynamicDataSourceHolder.DB_MASTER, master);
        targetDataSources.put(DynamicDataSourceHolder.DB_SLAVE, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }


    /**
     * MybatisPlus扩展分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor() {
        return new DynamicDataSourceInterceptor();
    }

    /**
     * 根据数据源创建SqlSessionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory() throws Exception {
        //配置mybatis,对应mybatis-config.xml
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        GlobalConfiguration globalConfig = GlobalConfigUtils.defaults();
        globalConfig.setMetaObjectHandler(metaObjectHandler());
        //懒加载
        LazyConnectionDataSourceProxy p = new LazyConnectionDataSourceProxy();
        p.setTargetDataSource(dynamicDataSource(master(), slave()));
        sqlSessionFactory.setDataSource(p);
        //需要mapper文件时加入扫描
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        sqlSessionFactory.setTypeAliasesPackage("com.imooc.wjtest.entity");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setGlobalConfig(globalConfig);
        //加入上面的两个拦截器
        Interceptor interceptor[] = {paginationInterceptor(), dynamicDataSourceInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        return sqlSessionFactory.getObject();
    }


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        // 用户名
        initParameters.put("loginUsername", "admin");
        // 密码
        initParameters.put("loginPassword", "123456");
        // 禁用HTML页面上的“Reset All”功能
        initParameters.put("resetEnable", "false");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        initParameters.put("allow", "");
        // IP黑名单 (存在共同时，deny优先于allow)
//        initParameters.put("deny", "192.168.20.38");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


    /**
     * 配置事务管理器
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     * 自定义过滤器
     *
     * @return
     */
    public WallFilter createWallFilter() {
        WallConfig wallConfig = new WallConfig();
        // 允许一次执行多条语句
        wallConfig.setMultiStatementAllow(true);
        wallConfig.setNoneBaseStatementAllow(true);

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);

        return wallFilter;
    }
}
