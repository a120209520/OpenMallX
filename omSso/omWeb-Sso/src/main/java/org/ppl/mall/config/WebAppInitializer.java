package org.ppl.mall.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * web启动配置(代替了web.xml)
 * @author PPL
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Spring ApplicationContext (父容器, 无法访问SpringMVC ApplicationContext)
    //ContextLoarderListener创建与Web无关的其他Bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //SpringMVC ApplicationContext (子容器, 可以访问Spring ApplicationContext, 同时也会覆盖父容器中同样的@Bean)
    //DispatcherServlet创建@Controller, ViewResolver等Web相关Bean
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    //DispatcherServlet映射的地址
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //Encoding Filter
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        super.onStartup(servletContext);
    }
}
