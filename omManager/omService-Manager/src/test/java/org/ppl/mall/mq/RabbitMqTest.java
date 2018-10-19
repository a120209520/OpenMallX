package org.ppl.mall.mq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.ppl.mall.config.RabbitConfig;
import org.ppl.mall.service.message.ItemAddMsgDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RabbitConfig.class)
public class RabbitMqTest {

    @Autowired
    private ItemAddMsgDispatcher dispatcher;

    @Test
    public void itemAdd() throws InterruptedException {
        dispatcher.sendMsgToSolr(1001);
        TimeUnit.SECONDS.sleep(10);
    }
}

