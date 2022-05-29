package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;

import java.util.*;

public class HashMapMessageRepository implements MessageRepository {
    private final Map<UUID, Message> messages = new HashMap<>();

    /**
     * Задаем UUID для message
     *
     * @param message мессадж =)
     * @return Значение ключа
     */
    @Override
    public UUID UUID_create(Message message) {
        message.setId(UUID.randomUUID());
        return message.getId();
    }

    /**
     * Создает запись в мапе, а также задает уникальное значение ключа для сообщения
     *
     * @param messages сообщение на вход
     * @return массив UUID-ключей
     * @see #UUID_create(Message)
     */
    @Override
    public UUID[] create(Message... messages) {
        UUID[] uuidRaid = new UUID[messages.length];
        int counter = 0;
        for (Message current : messages) {
            UUID_create(current);
            this.messages.put(current.getId(), current);
            uuidRaid[counter] = current.getId();
            counter++;
        }
        return uuidRaid;
    }


    /**
     * Возвращает обьект из мапы по ключу
     *
     * @param key UUID-ключ
     * @return Message
     */
    @Override
    public Message[] findByPrimaryKey(UUID... key) {
        Message[] messagesRaid = new Message[key.length];
        int count = 0;
        for (UUID current : key) {
            messagesRaid[count] = messages.get(current);
            count++;
        }
        return messagesRaid;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    public Message[] findAllinArray(){
        Collection<Message> test = findAll();
        Message[] output = new Message[test.size()];
        int count =0;
        for (Message counter: test) {
            output[count] = counter;
            count++;
        }
        return output;
    }

}
