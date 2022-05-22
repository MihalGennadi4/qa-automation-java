package com.tcs.edu.printer;

import com.tcs.edu.decorator.SeverityMessageDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.service.*;

/**
 * Класс com.tcs.edu.printer.ConsolePrinter
 * Включает в себя методы связанные с выводом сообщений в консоль.
 */
public class ConsolePrinter implements Printer {

    /**
     * Метод print
     * Предназначен для вывода сообщений в консоль.
     *
     * @param message - DTO содержащее сообщение и сопутствующую информацию
     * @author m.petrukhin
     */
    public void print(MessageService service, Message... message) {

        for (Message current : message) {
            System.out.println(service.processMessage(current));
        }
    }


    /**
     * Print с возможностью изменения порядка вывода сообщений.
     *
     * @param orderBy  определяет возрастающий или убывающий порядок вывода.
     * @param messages сообщение (или несколько) для вывода в консоль.
     */
    public void print(MessageService service, MessageOrder orderBy, Message... messages) {
        final Service serviceOrder = new OrderedDistinctedMessageService();
        Message[] output;
        output = serviceOrder.orderedMessage(orderBy, messages);
        for (Message current : output) {
            System.out.println(service.processMessage(current));
        }
    }


    /**
     * Print с возможностью убрать дубли из печати.
     *
     * @param doubling DISTINCT - убрать дубли, DOUBLES - оставить дубли.
     * @param messages сообщение (или несколько) для вывода в консоль.
     */
    public void print(MessageService service, Doubling doubling, Message... messages) {
        final Service serviceDistinct = new OrderedDistinctedMessageService();
        Message[] output;
        output = serviceDistinct.distinctedMessage(doubling, messages);
        for (Message current : output) {
            System.out.println(service.processMessage(current));
        }
    }


    /**
     * Print с возможностью сортировки сообщений и убирания дублей
     *
     * @param orderBy  отвечает за сортировку
     * @param doubling отвечает за убирание дублей
     * @param messages варарг сообщений
     */
    public void print(MessageService service, MessageOrder orderBy, Doubling doubling, Message... messages) {
        final Service serviceOD = new OrderedDistinctedMessageService();
        Message[] output;
        output = serviceOD.orderedMessage(orderBy, messages);
        output = serviceOD.distinctedMessage(doubling, output);
        for (Message current : output) {
            System.out.println(service.processMessage(current));
        }
    }
}










