package org.ppl.mall.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Spring根配置
 * @author PPL
 */
@Configuration
@ComponentScan(basePackages = {"org.ppl.mall"})
@Import({
        DataSourceConfig.class,
        RedisConfig.class,
        SolrConfig.class,
        DubboConfig.class,
        TransactionConfig.class,
        RabbitConfig.class
})
@PropertySource({
        "classpath:/conf/db.properties",
        "classpath:/conf/item.properties",
        "classpath:/conf/rabbit.properties",
        "classpath:/conf/redis.properties",
        "classpath:/conf/solr.properties"
})
public class RootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
