package com.example.vkbot.model;

public class VkMessage {
    private String type;
    private VkObject object;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VkObject getObject() {
        return object;
    }

    public void setObject(VkObject object) {
        this.object = object;
    }
}

