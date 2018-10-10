package org.ppl.mall.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 商品添加消息发送类
 * @author Smith
 */
public class ItemAddMessageDispatcher {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name="itemAddDestination")
    private Destination destination;

    public void sendMsg(final long id) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(id+"");
            }
        });
    }
}
