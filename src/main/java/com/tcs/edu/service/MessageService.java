package com.tcs.edu.service;

import com.tcs.edu.counter.Counter;
import com.tcs.edu.decorator.Decorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.Printer;

import static com.tcs.edu.counter.Counter.messageCounter;
import static com.tcs.edu.counter.Counter.showMessageCount;

/**
 * Обработка сообщений
 */
public class MessageService extends ValidatedService implements Service {

    private Printer printer;
    private Decorator decorateTime;
    private Decorator decorateSeverity;

    /**
     * @param printer   Способ печати
     * @param Severity  Декорация уровня сообщений
     * @param Timestamp Декорация времени
     */
    public MessageService(Printer printer, Decorator Severity, Decorator Timestamp) {
        this.printer = printer;
        this.decorateTime = Timestamp;
        this.decorateSeverity = Severity;
    }


    /**
     * Собирает и обрабатывает сообщение по поступившим на вход параметрам.
     * На текущий момент:
     * Добавляет значение счетчика
     * Добавляет дату
     * Делит на "страницы"
     * Добавляет значение уровня/важности сообщения
     *
     * @return возвращает отформатированную строку
     * @author m.petrukhin
     */
    public String processMessage(Message message) {
        messageCounter();
        String resault;
        if (showMessageCount() % Counter.PAGE_SIZE == 0) {
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
        if (isArgsValid() == true) {
            printer.print(messages);
        }
    }

    /**
     * Метод API для вывода сообщений в консоль с сортировкой по порядку
     *
     * @param messages варарг сообщений
     */
    public void log(MessageOrder orderBy, Message... messages) {
        if (isArgsValid() == true) {
            printer.print(orderBy, messages);
        }
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли
     *
     * @param messages варарг сообщений
     */
    public void log(Doubling doubling, Message... messages) {
        if (isArgsValid() == true) {
            printer.print(doubling, messages);
        }
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли и отсортировать сообщения
     *
     * @param messages варарг сообщений
     */
    public void log(MessageOrder orderBy, Doubling doubling, Message... messages) {
        if (isArgsValid() == true) {
            printer.print(orderBy, doubling, messages);
        }
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
