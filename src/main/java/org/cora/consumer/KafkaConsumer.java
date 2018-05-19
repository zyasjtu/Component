package org.cora.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Colin
 * @date 2018/5/19
 */
@Component
public class KafkaConsumer implements MessageListener<Integer, String> {

    @Override
    public void onMessage(ConsumerRecord<Integer, String> record) {
        System.out.println("on message:" + record);
    }

}
