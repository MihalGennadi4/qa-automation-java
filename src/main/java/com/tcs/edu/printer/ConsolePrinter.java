package com.tcs.edu.printer;


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
    public void print(Message... message) {
        final Service service = new MessageService();
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
    public void print(MessageOrder orderBy, Message... messages) {
        final Service service = new MessageService();
        final Service serviceOrder = new OrderedDistinctedMessageService();
        Message[] output = new Message[messages.length];
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
    public void print(Doubling doubling, Message... messages) {
        final Service service = new MessageService();
        final Service serviceDistinct = new OrderedDistinctedMessageService();
        Message[] output = new Message[messages.length];
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
    public void print(MessageOrder orderBy, Doubling doubling, Message... messages) {

        final Service serviceOD = new OrderedDistinctedMessageService();
        final Service service = new MessageService();
        Message[] output = new Message[messages.length];
        output = serviceOD.orderedMessage(orderBy, messages);
        output = serviceOD.distinctedMessage(doubling, output);
        for (Message current : output) {
            System.out.println(service.processMessage(current));
        }


    }
}










