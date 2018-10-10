package org.ppl.mall.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class ActiveMqTest {

	//@Test
	public void testQueueSend() throws Exception {
		//1.创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.133:61616");
		//2.创建Connection
		Connection connection = factory.createConnection();
		//3.开启连接
		connection.start();
		//4.创建Session 
		//第1个参数: 是否开启分布式事务，一般不开启
		//第2个参数: 自动应答/手动应答，一般自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用Session创建Queue
		Queue queue = session.createQueue("testQ");
		//6.创建Producer
		MessageProducer producer = session.createProducer(queue);
		//7.创建Message
		/*TextMessage msg = new ActiveMQTextMessage();
		msg.setText("hello");*/
		TextMessage msg = session.createTextMessage("hello");
		//8.发送Message
		producer.send(msg);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	
	//@Test
	public void testQueueRecv() throws Exception {
		//1.创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.133:61616");
		//2.创建Connection
		Connection connection = factory.createConnection();
		//3.开启连接
		connection.start();
		//4.创建Session 
		//第1个参数: 是否开启分布式事务，一般不开启
		//第2个参数: 自动应答/手动应答，一般自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用Session创建Queue
		Queue queue = session.createQueue("testQ");
		//6.创建Consumer
		MessageConsumer consumer = session.createConsumer(queue);
		//7.接收Message(非阻塞)
		System.out.println("waiting msg");
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage txtMsg = (TextMessage) message;
				try {
					System.out.println(txtMsg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.in.read();
		System.out.println("finish!");
		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();
	}

	@Test
	public void testTopicSend() throws Exception {
		//1.创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.133:61616");
		//2.创建Connection
		Connection connection = factory.createConnection();
		//3.开启连接
		connection.start();
		//4.创建Session 
		//第1个参数: 是否开启分布式事务，一般不开启
		//第2个参数: 自动应答/手动应答，一般自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用Session创建Topic
		Topic topic = session.createTopic("testT");
		//6.创建Producer
		MessageProducer producer = session.createProducer(topic);
		//7.创建Message
		/*TextMessage msg = new ActiveMQTextMessage();
		msg.setText("hello");*/
		TextMessage msg = session.createTextMessage("hello");
		//8.发送Message
		producer.send(msg);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void testTopicRecv() throws Exception {
		//1.创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.133:61616");
		//2.创建Connection
		Connection connection = factory.createConnection();
		//3.开启连接
		connection.start();
		//4.创建Session 
		//第1个参数: 是否开启分布式事务，一般不开启
		//第2个参数: 自动应答/手动应答，一般自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用Session创建Topic
		Topic topic = session.createTopic("testT");
		//6.创建Consumer
		MessageConsumer consumer = session.createConsumer(topic);
		//7.接收Message(非阻塞)
		System.out.println("waiting msg");
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage txtMsg = (TextMessage) message;
				try {
					System.out.println(txtMsg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.in.read();
		System.out.println("finish!");
		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();
	}
}
