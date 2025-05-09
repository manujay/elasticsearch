package com.springdemo.elasticsearch.message;

import com.springdemo.elasticsearch.camel.route.EventConsumerRoute;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumerRoute.class.getName());

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String queueName, final String message) {
        log.debug("sending message to queue {}", queueName);
        jmsTemplate.send(queueName, session -> {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            return textMessage;
        });
    }
}
