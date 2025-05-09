package com.springdemo.elasticsearch.events;

import com.springdemo.elasticsearch.Payload;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Function;

@Data
public class EventTypeDto implements EventTypeInfo, Serializable {
    private Long id;
    private String type;
    private String event;
    private Long createTs;
    private Long updateTs;
    private String udevId;

    public EventTypeDto(EventType eventType) {
        this(eventType.getId(), eventType.getType(), eventType.getEvent(), eventType.getCreateTs(), eventType.getUpdateTs(), eventType.getUdevId());
    }

    public EventTypeDto(Long id, String type, String event, Long createTs, Long updateTs, String udevId) {
        this.id = id;
        this.type = type;
        this.event = event;
        this.createTs = createTs;
        this.updateTs = updateTs;
        this.udevId = udevId;
    }

    public EventTypeDto(String type, String event, Long createTs, String udevId) {
        this.type = type;
        this.event = event;
        this.createTs = createTs;
        this.updateTs = createTs;
        this.udevId = udevId;
    }

    public static EventTypeDto map(Function<Payload, EventTypeDto> function, Payload payload) {
        return function.apply(payload);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public Long getCreateTs() {
        return createTs;
    }

    @Override
    public Long getUpdateTs() {
        return updateTs;
    }

    @Override
    public String getUdevId() {
        return udevId;
    }
}
