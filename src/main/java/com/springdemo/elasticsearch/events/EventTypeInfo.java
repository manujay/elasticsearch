package com.springdemo.elasticsearch.events;

public interface EventTypeInfo {
    Long getId();

    String getType();

    String getEvent();

    Long getCreateTs();

    Long getUpdateTs();

    String getUdevId();
}
