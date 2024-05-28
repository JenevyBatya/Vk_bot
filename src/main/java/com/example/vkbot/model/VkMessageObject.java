package com.example.vkbot.model;

public class VkMessageObject {
    private String text;
    private int peer_id;
    private int from_id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPeer_id() {
        return peer_id;
    }

    public void setPeer_id(int peer_id) {
        this.peer_id = peer_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }
}