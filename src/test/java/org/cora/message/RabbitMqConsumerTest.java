package org.cora.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RabbitMqConsumerTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void onMessage() throws Exception {
        amqpTemplate.convertAndSend("queue_two_key", "queue_two_value");
    }

}