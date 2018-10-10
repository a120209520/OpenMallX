package org.ppl.mall.service.message;

import org.ppl.mall.service.search.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

/**
 * 商品添加消息监听类
 * @author Smith
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try {
            //延时用于临时测试，避免事务提交前收到消息
            TimeUnit.MILLISECONDS.sleep(500);
            TextMessage txtMsg = (TextMessage) message;
            Long id = Long.parseLong(txtMsg.getText());
            System.out.println("ItemAddMessageListener:"+id);
            searchItemService.importItem(id);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
