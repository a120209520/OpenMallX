package org.ppl.mall.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring根配置
 * @author PPL
 */
@Configuration
@ComponentScan(basePackages = {"org.ppl.mall"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),// 这个是为了不让扫描到springmvc相关的Bean
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)   // 这个是为了不让扫描到Controller，否则会创建两次Controller
})
@Import({FreeMarkerConfig.class})
public class RootConfig {
}
