package com.tcs.edu.printer;


import com.tcs.edu.decorator.Severity;
import com.tcs.edu.service.MessageOrder;

import static com.tcs.edu.service.MessageOrder.ASC;
import static com.tcs.edu.service.MessageOrder.DESC;
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

    /**
     * Ptint с возможностью изменения порядка вывода сообщений
     * @param level уровень сообщения
     * @param orderBy определяет возрастающий или убывающий порядок вывода
     * @param messages сообщение (или несколько) для вывода в консоль.
     */
    public static void print(Severity level, MessageOrder orderBy, String... messages) {
        if (orderBy == ASC) {
            for (String current : messages) {
                System.out.println(processMessage(level, current));
            }
        } else if (orderBy == DESC) {
            for (int counter = messages.length - 1; counter >= 0; counter--) {
                System.out.println(processMessage(level, messages[counter]));
            }
        }
    }
}

