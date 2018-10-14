package org.ppl.mall.config.service;

import org.ppl.mall.jedis.JedisClientPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;

/**
 * redis配置
 * @author PPL
 */
@Configuration
@PropertySource("classpath:/conf/redis.properties")
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    public JedisPool jedisPool() {
        System.out.println("new jedis pool!!!!!");
        return new JedisPool(
                env.getProperty("redis.host"),
                env.getProperty("redis.port", Integer.class)
        );
    }

    @Bean
    public JedisClientPool jedisClientPool() {
        JedisClientPool jedisClientPool = new JedisClientPool();
        jedisClientPool.setJedisPool(jedisPool());
        return jedisClientPool;
    }
}
