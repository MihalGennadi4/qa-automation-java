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
    public static void decorate(String message) {
        Instant instantTime = Instant.now();
        System.out.println(instantTime + " " + message);
    }



}
