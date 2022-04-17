package com.tcs.edu.printer;


import com.tcs.edu.decorator.Severity;
import com.tcs.edu.service.MessageService;

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
     * @param message текстовый параметр с сообщением для вывода в консоль.
     * @param level уровень сообщения.
     * @author m.petrukhin
     */
    public static void print(String message, Severity level) {
        System.out.println(processMessage(message, level));

    }
}
