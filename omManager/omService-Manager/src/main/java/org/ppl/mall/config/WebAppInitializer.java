package org.ppl.mall.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Spring ApplicationContext
    //ContextLoarderListener创建与Web无关的其他Bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //SpringMVC ApplicationContext
    //DispatcherServlet创建@Controller, ViewResolver等Web相关Bean
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    //DispatcherServlet映射的地址
    @Override
    protected String[] getServletMappings() {
        return null;
    }
}
