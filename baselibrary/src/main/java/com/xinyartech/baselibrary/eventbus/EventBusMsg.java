package com.xinyartech.baselibrary.eventbus;

public class EventBusMsg {
    public String key;
    public Object value;

    public EventBusMsg(String key) {
        this.key = key;
    }

    public EventBusMsg(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
