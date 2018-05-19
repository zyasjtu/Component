package org.cora.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Colin
 * @date 2018/5/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-kafka.xml"})
public class KafkaConsumerTest {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Test
    public void onMessage() throws Exception {
        kafkaTemplate.sendDefault("key", "data");
    }
}
