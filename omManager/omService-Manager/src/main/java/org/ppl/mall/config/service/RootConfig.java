package org.ppl.mall.config.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
        DubboConfig.class
})
public class RootConfig {
}
