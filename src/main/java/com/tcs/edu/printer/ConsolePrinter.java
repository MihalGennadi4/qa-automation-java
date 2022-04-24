package com.tcs.edu.printer;


import com.tcs.edu.decorator.Severity;
import com.tcs.edu.service.Doubling;
import com.tcs.edu.service.MessageOrder;

import java.util.Objects;

import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.Doubling.DOUBLES;
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
     *
     * @param level    уровень сообщения
     * @param orderBy  определяет возрастающий или убывающий порядок вывода
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

    public static void print(Severity level, Doubling doubling, String... messages) {
        //Проверку сообщения на вхождение в массив выведенных сообщений реализовать циклом с проверкой на эквивалентность

        if (doubling == DOUBLES) {
            for (String current : messages) {
                System.out.println(processMessage(level, current));
            }
        } else if (doubling == DISTINCT) {
            String[] printedMessages = new String[messages.length];


            for (int counter = 0; counter < messages.length; counter++) {
                if (!Objects.equals(messages[counter], printedMessages)) {
                    printedMessages[counter] = messages[counter];
                    System.out.println(processMessage(level,messages[counter]));
                }
            }
        }


    }
}



