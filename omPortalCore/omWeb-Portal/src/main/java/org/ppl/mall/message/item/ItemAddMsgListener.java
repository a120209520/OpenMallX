package org.ppl.mall.message.item;

import org.ppl.mall.tools.item.HtmlGenerator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ItemAddMsgListener {

    @Autowired
    private HtmlGenerator htmlGenerator;

    @RabbitListener(queues="#{staticHtmlItemAddQueue.name}")
    public void staticHtmlItemAdd(Long id) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("staticHtmlItemAdd: " + id);
            //htmlGenerator.genItemDetail(id);
            htmlGenerator.genPortalIndex();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



