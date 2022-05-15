package com.tcs.edu.service;

import com.tcs.edu.domain.Message;

public interface Service {
    String processMessage(Message messages);

    void log(Message... message);

    void log(Doubling doubling, Message... message);

    void log(MessageOrder orderBy, Message... message);

    void log(MessageOrder orderBy, Doubling doubling, Message... message);

    Message[] orderedMessage(MessageOrder orderBy, Message... messages);

    Message[] distinctedMessage(Doubling doubling, Message... messages);
}
