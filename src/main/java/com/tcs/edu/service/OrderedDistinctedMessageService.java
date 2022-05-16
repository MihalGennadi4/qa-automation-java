package com.tcs.edu.service;

import com.tcs.edu.domain.Message;

import java.util.Objects;

import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.Doubling.DOUBLES;
import static com.tcs.edu.service.MessageOrder.ASC;
import static com.tcs.edu.service.MessageOrder.DESC;

public class OrderedDistinctedMessageService extends ValidatedService implements Service {

    /**
     * Убирает дубли из выходных сообщений. Body у дублей заменяет заменяет на "".
     *
     * @param doubling вкл/выкл обработку дублей
     * @param messages варарг сообщений на вход
     * @return возвращает варарг сообщений такой же длинны как и варарг полученный на вход
     */
    public Message[] distinctedMessage(Doubling doubling, Message... messages) {
            Message[] output = new Message[messages.length];
            String[] toFind = new String[messages.length];
        if (super.isArgsValid(messages)) {
            if (doubling == DOUBLES) {
                for (int count = 0; count < messages.length; count++) {
                    output[count] = messages[count];
                }
            } else if (doubling == DISTINCT) {
                boolean found = false;
                int order = 0;
                for (int count = 0; count < messages.length; count++) {
                    String searchedValue = messages[order].getBody();
                    for (String x : toFind) {
                        if (Objects.equals(x, searchedValue)) {
                            found = true;
                            output[count] = messages[count];
                            output[order].setBody("");
                            output[order].setLevel(messages[count].getLevel());
                            order++;
                            break;
                        } else if (!Objects.equals(x, searchedValue)) {
                            found = false;
                        }
                    }
                    if (!found) {
                        output[count] = messages[count];

                        if (messages[count].getBody() != null) {
                            toFind[count] = messages[count].getBody();
                        }
                        order++;
                    }
                }
                for (int count = 0; count < messages.length; count++) {
                    output[count] = messages[count];
                }

            }
            return output;
        }
        return output;
    }

    /**
     * Сортирует сообщения по порядку.
     *
     * @param orderBy  DESC - обратный порядок, ASC - обычный порядок
     * @param messages варарг сообщений на вход
     * @return обработанный варарг сообщений на выход
     */
    public Message[] orderedMessage(MessageOrder orderBy, Message... messages) {
        Message[] output = new Message[messages.length];
        int countReverse = 0;
        if (orderBy == DESC) {
            for (int counter = messages.length - 1; counter >= 0; counter--) {
                output[counter] = messages[countReverse];
                countReverse++;
            }


        }
        return output;
    }

    @Override
    public String processMessage(Message messages) {
        return null;
    }

    @Override
    public void log(Message... message) {

    }

    @Override
    public void log(Doubling doubling, Message... message) {

    }

    @Override
    public void log(MessageOrder orderBy, Message... message) {

    }

    @Override
    public void log(MessageOrder orderBy, Doubling doubling, Message... message) {

    }
}
