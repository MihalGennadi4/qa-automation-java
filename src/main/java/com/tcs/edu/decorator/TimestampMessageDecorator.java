package com.tcs.edu.decorator;

import java.time.Instant;


/**
 * Класс для изменениий принятых на вход сообщений.
 */

public class TimestampMessageDecorator {

    /**
     * Метод декорации сообщений.
     * Добавляет текущее время перед выводом сообщения принятого на вход
     *
     * @param message сообщение принимаемое на вход.
     * @author m.petrukhin
     */
    public static String addTimestamp(String message) {

        String decoratedMessage;
        decoratedMessage = String.format("%s %s", Instant.now(), message);
        return decoratedMessage;
    }

}