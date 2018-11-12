package ru.itmo.webmail.model.domain;

import java.io.Serializable;

public class News implements Serializable {

    private Long userId;
    private String text;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}
