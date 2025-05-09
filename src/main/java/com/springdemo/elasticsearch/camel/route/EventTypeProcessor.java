package com.springdemo.elasticsearch.camel.route;

import com.google.gson.Gson;
import com.springdemo.elasticsearch.events.EventTypeDto;
import com.springdemo.elasticsearch.message.MessageProducer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventTypeProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumerRoute.class.getName());
    @Autowired
    MessageProducer producer;

    @Override
    public void process(Exchange exchange) {
        LOGGER.info("processing message from exchange");
        EventTypeDto eventTypeDto = exchange.getIn().getBody(EventTypeDto.class);
        producer.sendMessage("inbound.queue", new Gson().toJson(eventTypeDto));
    }
}
