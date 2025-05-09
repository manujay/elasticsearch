package com.springdemo.elasticsearch.camel.route;

import com.springdemo.elasticsearch.Constant;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumerRoute extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumerRoute.class.getName());

    @Autowired
    EventTypeProcessor processor;

    @Override
    public void configure() {
        from(Constant.DIRECT_EVENT_TYPE_ROUTE).log("moving to inbound queue").process(processor).end();
    }
}
