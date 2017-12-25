package org.cora.message;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMqConsumer implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(RabbitMqConsumer.class);

    public void onMessage(Message message) {
        LOGGER.info("message body:\n" + message.toString());
        LOGGER.info("message properties:\n" + message.getMessageProperties().toString());
    }
}
