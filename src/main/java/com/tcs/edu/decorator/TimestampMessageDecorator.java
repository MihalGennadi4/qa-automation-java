package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс для изменениий принятых на вход сообщений.
 */

public class TimestampMessageDecorator {
    /**
     * Перменная-счетчик с кол-вом вызовов метода decorate.
     */
    public static int messageCount = 0;

    /**
     * Размер "страницы".
     */
    public static final int PAGE_SIZE = 2;

    /**
     * Метод декорации сообщений.
     * Добавляет текущее время перед выводом сообщения принятого на вход, счетчик вызоваа и разделение по "страницам"
     * Счётчик считает и выводит кол-во обращений к методу decorate.
     * @param message сообщение принимаемое на вход.
     * @author m.petrukhin
     */
    public static String decorate(String message) {
        messageCount++;
        String decoratedMessage;
        if (messageCount != 0 && messageCount % PAGE_SIZE == 0) {
            decoratedMessage = String.format("%d %s %s \n---", messageCount, Instant.now(), message);
        } else {
            decoratedMessage = String.format("%d %s %s", messageCount, Instant.now(), message);
        }
        return decoratedMessage;
    }


}
