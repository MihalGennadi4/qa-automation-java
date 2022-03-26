package com.tcs.edu;

import com.tcs.edu.decorator.TimestampMessageDecorator;

/** Основной класс приложения
 *  В нём творим различную учебную дичь
 *
 */
class Application {

    /** Основная функция приложения.
     *  Существует для вополщения различных фантазий.
     *  На данный момент выводит что-то в консоль и не падет.
     *  @param args пока нет понимания что сюда можно передать. Но наверняка, что-то можно передать.
     *  @author m.petrukhin
     */
    public static void main(String[] args) {

        TimestampMessageDecorator.decorate("Hello World!");
    }
}