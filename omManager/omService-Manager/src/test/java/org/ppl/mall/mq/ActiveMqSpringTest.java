package org.ppl.mall.mq;

import org.junit.Test;
import org.ppl.mall.service.message.ItemAddMessageDispatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class ActiveMqSpringTest {

	@Test
	public void testQueueSend() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mq.xml");
        JmsTemplate template = context.getBean(JmsTemplate.class);
        Destination destination = (Destination) context.getBean("testDestination");
        template.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("spring with ActiveMQ");
            }
        });
    }

    @Test
	public void testItemAdd() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mq.xml");
        JmsTemplate template = context.getBean(JmsTemplate.class);
        Destination destination = (Destination) context.getBean("itemAddDestination");
        template.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(21314+"");
            }
        });
        System.in.read();
    }
}
