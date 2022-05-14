package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.service.MessageService;

import static com.tcs.edu.decorator.SeverityLevel.MAJOR;
import static com.tcs.edu.decorator.SeverityLevel.MINOR;
import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.MessageOrder.DESC;


/**
 * Основа приложения
 * В нём творим различную учебную дичь.
 */
class Application {

    /**
     * Основная функция приложения.
     * Существует для вополщения различных фантазий.
     * На данный момент выводит что-то в консоль и это не ошибка.
     *
     * @param args пока нет понимания что сюда можно передать. Но наверняка, что-то можно передать.
     * @author m.petrukhin
     */
    public static void main(String[] args) {

        Message message1 = new Message(MAJOR, "One");
        Message message2 = new Message(MAJOR, "Two");
        Message message3 = new Message(MAJOR, "Two");
        Message message4 = new Message(MINOR, "Four");
        Message message5 = new Message(MINOR, "Five");
        Message message6 = new Message(MAJOR, "One");
        MessageService.log(message1, message2, message3, message4, message5, message6);
        MessageService.log(DESC, message1, message2, message3, message4, message5, message6);
        MessageService.log(DESC, DISTINCT, message1, message2, message3, message4, message5, message6);

    }
}