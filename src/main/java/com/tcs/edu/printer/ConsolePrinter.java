package com.tcs.edu.printer;

import com.tcs.edu.decorator.TimestampMessageDecorator;

/** Класс com.tcs.edu.printer.ConsolePrinter
 * Включает в себя методы связанные с выводом сообщений в консоль.
 *
 */
public class ConsolePrinter {

    /** Метод print
     * Предназначен для вывода сообщений в консоль.
     * @param message текстовый параметр с сообщением для вывода в консоль.
     * Side Effect on global variable TimestampMessageDecorator.messageCount
     * @author m.petrukhin
     */
    public static void print(String message) {
        TimestampMessageDecorator.messageCount ++;
        System.out.println(message);
    }
}
