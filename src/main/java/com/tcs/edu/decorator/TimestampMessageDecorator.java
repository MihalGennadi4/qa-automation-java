package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

import java.time.Instant;


/**
 * Класс для изменениий принятых на вход сообщений.
 */

public class TimestampMessageDecorator implements Decorator {

    /**
     * Метод декорации сообщений.
     * Добавляет текущее время перед выводом сообщения принятого на вход
     *
     * @param message сообщение принимаемое на вход.
     * @author m.petrukhin
     */
    public Object addTimestamp(Message message) {

        String decoratedMessage;
        if (message != null) {
            decoratedMessage = String.format("%s %s", Instant.now(), message.getBody());
        } else decoratedMessage = String.format("%s", Instant.now());
        return decoratedMessage;
    }

    @Override
    public String severityDecorate(Message message) {
        return null;
    }

}