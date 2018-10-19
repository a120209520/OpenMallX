package org.ppl.mall.service.message;

import org.ppl.mall.service.search.SearchItemService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ItemAddMsgListener {

    @Autowired
    private SearchItemService searchItemService;

    @RabbitListener(queues="#{solrItemAddQueue.name}")
    public void solrItemAdd(Long id) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("solrItemAdd: " + id);
            searchItemService.importItem(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



