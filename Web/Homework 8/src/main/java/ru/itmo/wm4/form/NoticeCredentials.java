package ru.itmo.wm4.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoticeCredentials {

    @NotEmpty
    @Size(min = 1, max = 5000)
    private String text;

    @NotEmpty
    @Pattern(regexp = "([a-z]+\\s?)+\\s*", message = "each tag must contain only lowercase latin letters")
    private String tagsStr;

    public String getTagsStr() {
        return tagsStr;
    }

    public void setTagsStr(String tagsStr) {
        this.tagsStr = tagsStr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
