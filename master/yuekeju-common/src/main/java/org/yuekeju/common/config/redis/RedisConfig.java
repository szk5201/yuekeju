package org.yuekeju.common.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;


/**
 * 2020-7-10
 * @author szk
 * redis 配置
 */
public class RedisConfig {

    @Value("${spring.redis.host}")
    public String host;
    @Value("${spring.redis.port}")
    public int port;
    @Value("${spring.redis.password}")
    public String password;
    @Value("${spring.redis.database}")
    public int database;
    @Value("${spring.redis.jedis.pool.max-idle}")
    public int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    public int minIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    public int maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    public String maxWait;
    @Value("${spring.redis.timeout}")
    public String timeout;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
        rf.setDatabase(database);
        rf.setHostName(host);
        rf.setPort(port);
        rf.setPassword(RedisPassword.of(password));
       // int timeouts = Integer.parseInt(timeout.substring(0, timeout.length() - 2));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        int l = Integer.parseInt(maxWait.substring(0, maxWait.length() - 2));
        jedisPoolConfig.setMaxWaitMillis(l);
        jpb.poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
        return jedisConnectionFactory;
    }
}
