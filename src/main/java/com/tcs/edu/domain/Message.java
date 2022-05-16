package com.tcs.edu.domain;

import com.tcs.edu.decorator.SeverityLevel;

public class Message {
    private SeverityLevel level;
    private String body;

    public Message(SeverityLevel level, String body) {
        this.level = level;
        this.body = body;
    }

    public SeverityLevel getLevel() {
        return level;
    }

    public String getBody() {
        return body;
    }

    public void setLevel(SeverityLevel level) {
        this.level = level;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
