package com.tcs.edu.service;

import com.tcs.edu.counter.Counter;
import com.tcs.edu.decorator.SeverityLevel;
import com.tcs.edu.domain.Message;

import static com.tcs.edu.counter.Counter.messageCounter;
import static com.tcs.edu.counter.Counter.showMessageCount;
import static com.tcs.edu.decorator.SeverityMessageDecorator.severityDecorate;
import static com.tcs.edu.decorator.TimestampMessageDecorator.addTimestamp;

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
  //   * @param message Текстовое сообщение из main-метода
 //    * @param level   Уровень/важность сообщения
     * @author m.petrukhin
     */
    public static String processMessage(Message message) {

        messageCounter();
        String resault;
        if (message.getBody() != null && 0 != showMessageCount() && showMessageCount() % Counter.PAGE_SIZE == 0) {
            resault = String.format("%d %s %s \n---", showMessageCount(), addTimestamp(message), severityDecorate(message));
        } else if (message.getBody() != null) {
            resault = String.format("%d %s %s", showMessageCount(), addTimestamp(message), severityDecorate(message));
        } else if (showMessageCount() % Counter.PAGE_SIZE == 0) {
            resault = String.format("%d %s %s \n---", showMessageCount(), addTimestamp(message), severityDecorate(message));
        } else {
            resault = String.format("%d %s %s", showMessageCount(), addTimestamp(message), severityDecorate(message));
        }

        return resault;
    }

    public static void log(Message... message) {

        print(severityDecorate(message));

    }
}
