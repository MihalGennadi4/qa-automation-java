package com.tcs.edu;

import com.tcs.edu.decorator.SeverityMessageDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;

import java.util.UUID;

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
        MessageService service = new MessageService(new ConsolePrinter(), new SeverityMessageDecorator(), new TimestampMessageDecorator(), new HashMapMessageRepository());
        HashMapMessageRepository repository = new HashMapMessageRepository();
        Message message1 = new Message(MAJOR, "Вывод 1");
        Message message2 = new Message(MINOR, "Вывод 2");
        Message message3 = new Message(MAJOR, "Вывод 3");
        Message message4 = new Message(MINOR, "Вывод 3");
        Message message5 = new Message(MINOR, "Вывод 2");
        Message message6 = new Message(MAJOR, "Вывод 4");
        Message brokenMessage = null;

        UUID[] raid = repository.create(message1, message2, message3, message4, message5, message6);
        Message[] mRaid = repository.findByPrimaryKey(raid);
        service.log(service, DESC, mRaid);
        service.log(service, DESC, DISTINCT, repository.collectionToArray(repository.findAll()));
        service.log(service, DESC, DISTINCT, repository.collectionToArray(repository.findBySeverity(MAJOR)));


    }

}