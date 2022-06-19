package com.tcs.edu.service;

import com.tcs.edu.counter.Counter;
import com.tcs.edu.decorator.Decorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.exeption.LogException;
import com.tcs.edu.printer.Printer;
import com.tcs.edu.repository.MessageRepository;

import static com.tcs.edu.counter.Counter.messageCounter;
import static com.tcs.edu.counter.Counter.showMessageCount;

/**
 * Обработка сообщений
 */
public class MessageService extends ValidatedService implements Service {
    final private Printer printer;
    final private Decorator decorateTime;
    final private Decorator decorateSeverity;
    final private MessageRepository repository;

    /**
     * @param printer    Способ печати
     * @param severity   Декорация уровня сообщений
     * @param timestamp  Декорация времени
     * @param repository Используемый репозиторий
     */
    public MessageService(Printer printer, Decorator severity, Decorator timestamp, MessageRepository repository) {
        this.printer = printer;
        this.decorateTime = timestamp;
        this.decorateSeverity = severity;
        this.repository = repository;
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
            resault = String.format("%d %s %s id = %s\n---", showMessageCount(), decorateTime.addTimestamp(message), decorateSeverity.severityDecorate(message), message.getId());
        } else {
            resault = String.format("%d %s %s id = %s", showMessageCount(), decorateTime.addTimestamp(message), decorateSeverity.severityDecorate(message), message.getId());
        }
        return resault;
    }

    /**
     * Метод для вывода сообщений в консоль
     *
     * @param messages варарг сообщений
     */
    public void log(MessageService service, Message... messages) {
        try {
            super.isArgsValid(messages);
        } catch (IllegalArgumentException e) {
            throw new LogException("Что-то пошло не так", e);
        }
        printer.print(service, messages);

    }


    /**
     * Метод API для вывода сообщений в консоль с сортировкой по порядку
     *
     * @param messages варарг сообщений
     */
    public void log(MessageService service, MessageOrder orderBy, Message... messages) {
        try {
            isArgsValid();
        } catch (IllegalArgumentException e) {
            throw new LogException("Что-то пошло не так", e);
        }
        printer.print(service, orderBy, messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли
     *
     * @param messages варарг сообщений
     */
    public void log(MessageService service, Doubling doubling, Message... messages) {
        try {
            isArgsValid();
        } catch (IllegalArgumentException e) {
            throw new LogException("Что-то пошло не так", e);
        }
        printer.print(service, doubling, messages);
    }

    /**
     * Метод API для вывода сообщений в консоль с возможностью убрать дубли и отсортировать сообщения
     *
     * @param messages варарг сообщений
     */
    public void log(MessageService service, MessageOrder orderBy, Doubling doubling, Message... messages) {
        try {
            isArgsValid();
        } catch (IllegalArgumentException e) {
            throw new LogException("Что-то пошло не так", e);
        }
        printer.print(service, orderBy, doubling, messages);
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
