package com.springdemo.elasticsearch.events;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "event_type")
@SqlResultSetMapping(name = "GetInfoResult", classes = {@ConstructorResult(targetClass = EventType.class, columns = {@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "type", type = String.class), @ColumnResult(name = "event", type = String.class), @ColumnResult(name = "create_timestamp", type = Long.class), @ColumnResult(name = "update_timestamp", type = Long.class), @ColumnResult(name = "unique_device_identifier", type = String.class)})})
@SqlResultSetMapping(name = "GetAllInfoResult", classes = {@ConstructorResult(targetClass = EventType.class, columns = {@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "type", type = String.class), @ColumnResult(name = "event", type = String.class), @ColumnResult(name = "create_timestamp", type = Long.class), @ColumnResult(name = "update_timestamp", type = Long.class), @ColumnResult(name = "unique_device_identifier", type = String.class)})})
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "event")
    private String event;

    @Column(name = "create_timestamp", nullable = false)
    private Long createTs;

    @Column(name = "update_timestamp", nullable = false)
    private Long updateTs;

    @Column(name = "unique_device_identifier", nullable = false)
    private String udevId;

    public EventType() {
    }

    public EventType(Long id, String type, String event, Long createTs, Long updateTs, String udevId) {
        this.id = id;
        this.type = type;
        this.event = event;
        this.createTs = createTs;
        this.updateTs = updateTs;
        this.udevId = udevId;
    }

    public EventType(String type, String event, Long createTs, Long updateTs, String udevId) {
        this.type = type;
        this.event = event;
        this.createTs = createTs;
        this.updateTs = updateTs;
        this.udevId = udevId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Long getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Long createTs) {
        this.createTs = createTs;
    }

    public Long getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Long updateTs) {
        this.updateTs = updateTs;
    }

    public String getUdevId() {
        return udevId;
    }

    public void setUdevId(String udevId) {
        this.udevId = udevId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventType eventType = (EventType) o;
        return Objects.equals(id, eventType.id) && Objects.equals(type, eventType.type) && Objects.equals(event, eventType.event) && Objects.equals(createTs, eventType.createTs) && Objects.equals(updateTs, eventType.updateTs) && Objects.equals(udevId, eventType.udevId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, event, createTs, updateTs, udevId);
    }

    @Override
    public String toString() {
        return "EventType{" + "id=" + id + ", type='" + type + '\'' + ", event='" + event + '\'' + ", createTs=" + createTs + ", updateTs=" + updateTs + ", udevId='" + udevId + '\'' + '}';
    }
}