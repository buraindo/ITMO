package ru.itmo.wm4.form;

import javax.validation.constraints.NotEmpty;

public class CommentCredentials {

    @NotEmpty
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
