package com.tcs.edu.printer;


import com.tcs.edu.domain.Message;
import com.tcs.edu.service.Doubling;
import com.tcs.edu.service.MessageOrder;

import static com.tcs.edu.service.MessageService.processMessage;
import static com.tcs.edu.service.OrderedDistinctedMessageService.distinctedMessage;
import static com.tcs.edu.service.OrderedDistinctedMessageService.orderedMessage;

/**
 * Класс com.tcs.edu.printer.ConsolePrinter
 * Включает в себя методы связанные с выводом сообщений в консоль.
 */
public class ConsolePrinter {

    /**
     * Метод print
     * Предназначен для вывода сообщений в консоль.
     *
     * @param message - DTO содержащее сообщение и сопутствующую информацию
     * @author m.petrukhin
     */

    public static void print(Message... message) {
        for (Message current : message) {
            System.out.println(processMessage(current));
        }
    }


    /**
     * Print с возможностью изменения порядка вывода сообщений.
     *
     * @param orderBy  определяет возрастающий или убывающий порядок вывода.
     * @param messages сообщение (или несколько) для вывода в консоль.
     */

    public static void print(MessageOrder orderBy, Message... messages) {
        Message[] output = new Message[messages.length];
        output = orderedMessage(orderBy, messages);
        for (Message current : output) {
            System.out.println(processMessage(current));
        }
    }


    /**
     * Print с возможностью убрать дубли из печати.
     *
     * @param doubling DISTINCT - убрать дубли, DOUBLES - оставить дубли.
     * @param messages сообщение (или несколько) для вывода в консоль.
     */
    public static void print(Doubling doubling, Message... messages) {
        Message[] output = new Message[messages.length];
        output = distinctedMessage(doubling, messages);
        for (Message current : output) {
            System.out.println(processMessage(current));
        }
    }


    /**
     * Print с возможностью сортировки сообщений и убирания дублей
     *
     * @param orderBy  отвечает за сортировку
     * @param doubling отвечает за убирание дублей
     * @param messages варарг сообщений
     */
    public static void print(MessageOrder orderBy, Doubling doubling, Message... messages) {
        Message[] output = new Message[messages.length];
        output = orderedMessage(orderBy, messages);
        output = distinctedMessage(doubling, output);
        for (Message current : output) {
            System.out.println(processMessage(current));
        }


    }
}










