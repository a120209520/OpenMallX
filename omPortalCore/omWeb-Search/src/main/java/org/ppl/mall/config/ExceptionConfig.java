package org.ppl.mall.config;

import org.ppl.mall.exception.GlobalExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 异常相关配置
 * @author PPL
 */
@Configuration
public class ExceptionConfig {

    @Bean
    public GlobalExceptionResolver globalExceptionResolver() {
        return new GlobalExceptionResolver();
    }
}
