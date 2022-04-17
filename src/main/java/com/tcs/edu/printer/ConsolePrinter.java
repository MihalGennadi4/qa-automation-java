package com.tcs.edu.printer;


import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.service.MessageService.processMessage;

/**
 * Класс com.tcs.edu.printer.ConsolePrinter
 * Включает в себя методы связанные с выводом сообщений в консоль.
 */
public class ConsolePrinter {

    /**
     * Метод print
     * Предназначен для вывода сообщений в консоль.
     *
     * @param messages сообщение (или несколько) для вывода в консоль.
     * @param level    уровень сообщения.
     * @author m.petrukhin
     */
    public static void print(Severity level, String... messages) {
        for (String current : messages) {
            System.out.println(processMessage(level, current));
        }

    }
}
