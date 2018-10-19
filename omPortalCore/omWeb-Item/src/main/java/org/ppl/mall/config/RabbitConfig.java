package org.ppl.mall.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * rabbitMQ配置
 * @author PPL
 */
@Configuration
@EnableRabbit
@PropertySource("classpath:conf/rabbit.properties")
public class RabbitConfig {

    @Autowired
    Environment env;

    //连接工厂
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(env.getProperty("rabbit.port", Integer.class));
        factory.setUsername(env.getProperty("rabbit.username"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setVirtualHost(env.getProperty("rabbit.virtualhost"));
        return factory;
    }

    //rabbit管理员
    //用于管理资源, 例如增删Exchange,Queue,Binding
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    //rabbit消息模板
    //用于收发消息
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    //rabbit监听器工厂
    //用于创建监听器，具有监听池功能
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }


    //商品添加
    @Bean
    public DirectExchange itemAddExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange exchange = new DirectExchange(env.getProperty("rabbit.itemAdd.exchange"));
        exchange.setAdminsThatShouldDeclare(rabbitAdmin);
        return exchange;
    }
    @Bean
    public Queue staticHtmlItemAddQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(env.getProperty("rabbit.itemAdd.staticHtml.queue"));
        queue.setAdminsThatShouldDeclare(rabbitAdmin);
        return queue;
    }
    @Bean
    public Binding itemAddBinding(RabbitAdmin rabbitAdmin,
                                  DirectExchange itemAddExchange,
                                  Queue staticHtmlItemAddQueue) {
        Binding binding = BindingBuilder.bind(staticHtmlItemAddQueue).
                to(itemAddExchange).
                with(env.getProperty("rabbit.itemAdd.staticHtml.routingKey"));
        binding.setAdminsThatShouldDeclare(rabbitAdmin);
        return binding;
    }
}
