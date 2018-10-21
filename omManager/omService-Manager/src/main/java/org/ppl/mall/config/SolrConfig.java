package org.ppl.mall.config;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * solr配置
 */
@Configuration
public class SolrConfig {

    @Autowired
    private Environment env;

    @Bean
    public HttpSolrServer httpSolrServer() {
        return new HttpSolrServer(env.getProperty("solr.baseurl"));
    }
}
