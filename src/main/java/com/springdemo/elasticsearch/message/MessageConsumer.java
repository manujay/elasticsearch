package com.springdemo.elasticsearch.message;

import com.springdemo.elasticsearch.camel.route.EventConsumerRoute;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumerRoute.class.getName());

    @JmsListener(destination = "inbound.queue")
    @SendTo("outbound.queue")
    public String onReceive(final Message jsonMessage) throws JMSException {
        log.debug("onReceive message {} from topic {}", jsonMessage.getJMSMessageID(), "inbound.queue");
        String response = null;
        if (jsonMessage instanceof TextMessage textMessage) {
            response = textMessage.getText();
        }
        return response;
    }
}
