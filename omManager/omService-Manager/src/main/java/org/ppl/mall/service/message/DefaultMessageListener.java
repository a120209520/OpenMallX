package org.ppl.mall.service.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class DefaultMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage txtMsg = (TextMessage) message;
        try {
            System.out.println("recv:"+txtMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
