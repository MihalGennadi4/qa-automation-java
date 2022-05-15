package com.tcs.edu.service;

import com.tcs.edu.counter.Counter;
import com.tcs.edu.decorator.Decorator;
import com.tcs.edu.decorator.SeverityMessageDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.printer.Printer;

import static com.tcs.edu.counter.Counter.messageCounter;
import static com.tcs.edu.counter.Counter.showMessageCount;

/**
 * Обработка сообщений
 */
public class MessageService implements Service {
    private final Printer printer = new ConsolePrinter();
    private final Decorator decorateTime = new TimestampMessageDecorator();
    private final Decorator decorateSeverity = new SeverityMessageDecorator();



    /**
     * Собирает и обрабатывает сообщение по поступившим на вход параметрам.
     * На текущий момент:
     * Добавляет значение счетчика
     * Добавляет дату
     * Делит на "страницы"
     * Добавляет значение уровня/важности сообщения
     *
     * @author m.petrukhin
     * @return возвращает отформатированную строку
     */
    public String processMessage(Message message) {

        messageCounter();
        String resault;
        if (message != null && 0 != showMessageCount() && showMessageCount() % Counter.PAGE_SIZE == 0) {
            resault = String.format("%d %s %s \n---", showMessageCount(), decorateTime.addTimestamp(message), decorateSeverity.severityDecorate(message));
        } else if (message != null) {
            resault = String.format("%d %s %s", showMessageCount(), decorateTime.addTimestamp(message), decorateSeverity.severityDecorate(message));
        } else if (showMessageCount() % Counter.PAGE_SIZE == 0) {
            resault = String.format("%d %s %s \n---", showMessageCount(), decorateTime.addTimestamp(message), decorateSeverity.severityDecorate(message));
        } else {
            resault = String.format("%d %s %s", showMessageCount(), decorateTime.addTimestamp(message), decorateSeverity.severityDecorate(message));
        }
        return resault;
    }

    /**
     * Метод для вывода сообщений в консоль
     *
     * @param messages варарг сообщений
     */
    public void log(Message... messages) {
        printer.print(messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с сортировкой по порядку
     *
     * @param messages варарг сообщений
     */
    public void log(MessageOrder orderBy, Message... messages) {
        printer.print(orderBy, messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли
     *
     * @param messages варарг сообщений
     */
    public void log(Doubling doubling, Message... messages) {
        printer.print(doubling, messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли и отсортировать сообщения
     *
     * @param messages варарг сообщений
     */
    public void log(MessageOrder orderBy, Doubling doubling, Message... messages) {
        printer.print(orderBy, doubling, messages);
    }

    @Override
    public Message[] orderedMessage(MessageOrder orderBy, Message... messages) {
        return new Message[0];
    }

    @Override
    public Message[] distinctedMessage(Doubling doubling, Message... messages) {
        return new Message[0];
    }
}
