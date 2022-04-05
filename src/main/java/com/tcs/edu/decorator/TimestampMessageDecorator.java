package com.tcs.edu.decorator;

import java.time.Instant;

/** Класс для изменениий принятых на вход сообщений.
 *
 */
public class TimestampMessageDecorator {
    /** Перменная с кол-вом вызовов метода print
     *
     */
    public static int messageCount = 1; //Хотел чтоб по умолчанию было 0, но не получилось сделать правильный вывод в консоль

    /** Метод добавляющий текущее время перед выводом сообщения принятого на вход.
     * @param message сообщение принимаемое на вход.
     * @author m.petrukhin
     */
    public static String decorate(String message) {
       final var decoratedMessage =" " + Instant.now() + " " + message;
        return decoratedMessage;
    }



}
