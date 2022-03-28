package com.tcs.edu;

import com.tcs.edu.decoratorr.TimestampMessageDecoratorr;
import com.tcs.edu.printerr.ConsolePrinterr;
/** Основа приложения
 *  В нём творим различную учебную дичь.
 *
 */
class Applicationn {

    /** Основная функция приложения.
     *  Существует для вополщения различных фантазий.
     *  На данный момент выводит что-то в консоль и это не ошибка.
     *  @param args пока нет понимания что сюда можно передать. Но наверняка, что-то можно передать.
     *  @author m.petrukhin
     */
    public static void main(String[] args) {

        ConsolePrinterr.print(TimestampMessageDecoratorr.decorate("Hello World!"));
    }
}