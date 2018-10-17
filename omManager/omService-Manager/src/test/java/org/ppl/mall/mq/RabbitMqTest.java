package org.ppl.mall.mq;


import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RabbitMqTest {

    final static String QUEUE_NAME = "hello-q";
    private Sender sender = new Sender();
    private Recver recver = new Recver();
    private Connection connection = connect();

    public RabbitMqTest() throws Exception {}

    public Connection connect() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("139.199.195.113");
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = factory.newConnection();
        return connection;
    }

    @Test
    public void helloworld() throws Exception {
        System.out.println("connect="+connection);
        sender.send01(connection, "Hello Rabbit!");
        recver.recv01(connection);
        System.out.println("Finish!!");
        connection.close();
    }
}

class Sender {

    public void send01(Connection connection, String msg) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqTest.QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", RabbitMqTest.QUEUE_NAME, null, msg.getBytes());
        System.out.println("send01 --> "+msg);
        channel.close();
    }
}

class Recver {

    public void recv01(Connection connection) throws IOException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqTest.QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("recv01 <-- "+msg);
            }
        };
        channel.basicConsume(RabbitMqTest.QUEUE_NAME, true, consumer);
        TimeUnit.SECONDS.sleep(10);
        channel.close();
    }
}
