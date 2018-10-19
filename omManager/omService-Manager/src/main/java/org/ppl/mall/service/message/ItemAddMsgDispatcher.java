package org.ppl.mall.service.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 商品添加消息分发类
 * @author Smith
 */
@Component
public class ItemAddMsgDispatcher {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbit.itemAdd.exchange}")
    private String ITEMADD_EXCHANGE;

    @Value("${rabbit.itemAdd.solr.routingKey}")
    private String SOLR_ROUTINGKEY;

    @Value("${rabbit.itemAdd.staticHtml.routingKey}")
    private String STATIC_HTML_ROUTINGKEY;

    public void sendMsgToSolr(long id) {
        amqpTemplate.convertAndSend(ITEMADD_EXCHANGE, SOLR_ROUTINGKEY, id);
    }

    public void sendMsgToStaticHtml(long id) {
        amqpTemplate.convertAndSend(ITEMADD_EXCHANGE, STATIC_HTML_ROUTINGKEY, id);
    }
}
