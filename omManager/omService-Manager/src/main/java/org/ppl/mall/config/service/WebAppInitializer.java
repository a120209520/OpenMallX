package org.ppl.mall.config.service;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Spring ApplicationContext
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //SpringMVC ApplicationContext
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
