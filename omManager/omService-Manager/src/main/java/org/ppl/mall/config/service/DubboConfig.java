package org.ppl.mall.config.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.ppl.mall.service.ContentCatService;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.service.ItemCatService;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.search.SearchItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo配置
 * @author PPL
 */
@Configuration
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("omManager");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }


    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20881);
        return protocolConfig;
    }

    //商品服务
    @Bean
    public ServiceConfig<ItemService> itemServiceConfig(ItemService itemService) {
        ServiceConfig<ItemService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(ItemService.class);
        serviceConfig.setRef(itemService);
        serviceConfig.setTimeout(60000);
        return serviceConfig;
    }

    //商品分类服务
    @Bean
    public ServiceConfig<ItemCatService> itemCatServiceConfig(ItemCatService itemCatService) {
        ServiceConfig<ItemCatService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(ItemCatService.class);
        serviceConfig.setRef(itemCatService);
        serviceConfig.setTimeout(60000);
        return serviceConfig;
    }

    //内容分类服务
    @Bean
    public ServiceConfig<ContentCatService> contentCatServiceConfig(ContentCatService contentCatService) {
        ServiceConfig<ContentCatService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(ContentCatService.class);
        serviceConfig.setRef(contentCatService);
        serviceConfig.setTimeout(60000);
        return serviceConfig;
    }

    //内容服务
    @Bean
    public ServiceConfig<ContentService> contentServiceConfig(ContentService contentService) {
        ServiceConfig<ContentService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(ContentService.class);
        serviceConfig.setRef(contentService);
        serviceConfig.setTimeout(60000);
        return serviceConfig;
    }

    //商品搜索服务
    @Bean
    public ServiceConfig<SearchItemService> searchItemServiceConfig(SearchItemService searchItemService) {
        ServiceConfig<SearchItemService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(SearchItemService.class);
        serviceConfig.setRef(searchItemService);
        serviceConfig.setTimeout(60000);
        return serviceConfig;
    }
}
