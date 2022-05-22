package com.tcs.edu.domain;

import com.tcs.edu.decorator.SeverityLevel;

import java.util.Objects;

public class Message {
    private SeverityLevel level;
    private String body;

    public Message(SeverityLevel level, String body) {
        this.level = level;
        this.body = body;
    }

    public Message() {
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

    @Override
    public String toString() {
        return "Message{" + "\n" +
                "\"level\":" + "\"" + level + "\",\n" +
                "\"body\":" + "\"" + body + "\"\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return level == message.level && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, body);
    }
}
