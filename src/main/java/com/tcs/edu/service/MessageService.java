package com.tcs.edu.service;

import com.tcs.edu.counter.Counter;
import com.tcs.edu.decorator.Decorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.exeption.LogException;
import com.tcs.edu.printer.Printer;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.repository.MessageRepository;

import java.util.UUID;

import static com.tcs.edu.counter.Counter.messageCounter;
import static com.tcs.edu.counter.Counter.showMessageCount;

/**
 * Обработка сообщений
 */
public class MessageService extends ValidatedService implements Service {
    final private Printer printer;
    final private Decorator decorateTime;
    final private Decorator decorateSeverity;
    private MessageRepository messageRepository = new HashMapMessageRepository();

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
        UUID[] raid = messageRepository.create(messages);
        Message[] mRaid = messageRepository.findByPrimaryKey(raid);
        printer.print(service, mRaid);

    }

/*

    варианты:
    обрабатывать массив ключей в  логе
    сделать метод собирающий масив сообщений по одному ключу
*/


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
        UUID[] raid = messageRepository.create(messages);
        Message[] mRaid = messageRepository.findByPrimaryKey(raid);
        printer.print(service, orderBy, mRaid);
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
        UUID[] raid = messageRepository.create(messages);
        Message[] mRaid = messageRepository.findByPrimaryKey(raid);
        printer.print(service, doubling, mRaid);
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
        UUID[] raid = messageRepository.create(messages);
        Message[] mRaid = messageRepository.findByPrimaryKey(raid);
       // printer.print(service, orderBy, doubling, mRaid); //для вывода по ключу
        printer.print(service, orderBy, doubling, messageRepository.findAlltoArray()); //для вывода из коллекции
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
