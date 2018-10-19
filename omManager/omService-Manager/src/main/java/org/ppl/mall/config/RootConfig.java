package org.ppl.mall.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

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
public class RootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
