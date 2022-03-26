package com.tcs.edu.decorator;

import java.time.Instant;

/** Класс для изменениий принятых на вход сообщений.
 *
 */
public class TimestampMessageDecorator {

    /** Метод добавляющий текущее время перед выводом сообщения принятого на вход
     * @param message сообщение принимаемое на вход
     * @author m.petrukhin
     */
    public static String decorate(String message) {
        var addTime = Instant.now() + " " + message;
        return addTime;
    }



}
