package org.ppl.mall.message.item;

import org.ppl.mall.tools.item.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

/**
 * 静态页面监听器
 * @author PPL
 */
public class HtmlGenListener implements MessageListener {

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Override
    public void onMessage(Message message) {
        try {
            //延时用于临时测试，避免事务提交前收到消息
            TimeUnit.MILLISECONDS.sleep(500);
            TextMessage txtMsg = (TextMessage) message;
            Long id = Long.parseLong(txtMsg.getText());
            System.out.println("HtmlGenListener:"+id);
            htmlGenerator.itemAdd(id);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
