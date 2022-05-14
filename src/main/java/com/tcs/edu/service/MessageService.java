package com.tcs.edu.service;

import com.tcs.edu.counter.Counter;
import com.tcs.edu.domain.Message;

import static com.tcs.edu.counter.Counter.messageCounter;
import static com.tcs.edu.counter.Counter.showMessageCount;
import static com.tcs.edu.decorator.SeverityMessageDecorator.severityDecorate;
import static com.tcs.edu.decorator.TimestampMessageDecorator.addTimestamp;
import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * Обработка сообщений
 */
public class MessageService {

    /**
     * Собирает и обрабатывает сообщение по поступившим на вход параметрам.
     * На текущий момент:
     * Добавляет значение счетчика
     * Добавляет дату
     * Делит на "страницы"
     * Добавляет значение уровня/важности сообщения
     *
     * @author m.petrukhin
     */
    public static String processMessage(Message message) {

        messageCounter();
        String resault;
        if (message != null && 0 != showMessageCount() && showMessageCount() % Counter.PAGE_SIZE == 0) {
            resault = String.format("%d %s %s \n---", showMessageCount(), addTimestamp(message), severityDecorate(message));
        } else if (message != null) {
            resault = String.format("%d %s %s", showMessageCount(), addTimestamp(message), severityDecorate(message));
        } else if (showMessageCount() % Counter.PAGE_SIZE == 0) {
            resault = String.format("%d %s %s \n---", showMessageCount(), addTimestamp(message), severityDecorate(message));
        } else {
            resault = String.format("%d %s %s", showMessageCount(), addTimestamp(message), severityDecorate(message));
        }

        return resault;
    }

    /**
     * Метод для вывода сообщений в консоль
     *
     * @param messages варарг сообщений
     */
    public static void log(Message... messages) {
        print(messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с сортировкой по порядку
     *
     * @param messages варарг сообщений
     */
    public static void log(MessageOrder orderBy, Message... messages) {
        print(orderBy, messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли
     *
     * @param messages варарг сообщений
     */
    public static void log(Doubling doubling, Message... messages) {
        print(doubling, messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли и отсортировать сообщения
     *
     * @param messages варарг сообщений
     */
    public static void log(MessageOrder orderBy, Doubling doubling, Message... messages) {
        print(orderBy, doubling, messages);
    }
}
