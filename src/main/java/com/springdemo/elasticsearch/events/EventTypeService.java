package com.springdemo.elasticsearch.events;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventTypeService {

    List<EventTypeDto> getEventTypeInfo(String udevId);

    Long addEventInfo(EventTypeDto dto);

    Long deleteUdevId(String udevId);

}
