package com.springdemo.elasticsearch;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class Payload {

    @JsonProperty(namespace = "udevId")
    String udevId;
    @JsonProperty(namespace = "info")
    String info;
    @JsonProperty(namespace = "type")
    String type;
    @JsonProperty(namespace = "createTs")
    Long createTs;

    public Payload() {
    }

    public String getUdevId() {
        return udevId;
    }

    public void setUdevId(String udevId) {
        this.udevId = udevId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Long createTs) {
        this.createTs = createTs;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "udevId='" + udevId + '\'' +
                ", info='" + info + '\'' +
                ", type='" + type + '\'' +
                ", createTs=" + createTs +
                '}';
    }
}
