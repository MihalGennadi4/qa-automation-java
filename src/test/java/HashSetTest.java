import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.repository.MessageRepository;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static com.tcs.edu.decorator.SeverityLevel.MAJOR;
import static com.tcs.edu.decorator.SeverityLevel.MINOR;
import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    MessageRepository repository = new HashMapMessageRepository();

    Message message1 = new Message(MAJOR, "Вывод 1");
    Message message2 = new Message(MINOR, "Вывод 2");
    Message message3 = new Message(MAJOR, "Вывод 3");
    Message message4 = new Message(MINOR, "Вывод 3");
    Message message5 = new Message(MINOR, "Вывод 2");
    Message message6 = new Message(MAJOR, "Вывод 4");


    @Test
    public void shouldSaveElementWhenDoesntExists() {
        UUID[] id = repository.create(message1);
        Message[] toInspect = repository.findByPrimaryKey(id);
        assertAll(
                () -> assertEquals(message1.getBody(), toInspect[0].getBody()),
                () -> assertEquals(message1.getLevel(), toInspect[0].getLevel()),
                () -> assertEquals(toInspect[0].getId(), message1.getId())
        );


    }

    @Test
    public void shouldSaveElementWhenItExists() {
        repository.create(message1);
        repository.create(message1);
        Collection<Message> toInspect = repository.findAll();
        assertEquals(2, toInspect.size());
    }

    @Test
    public void shouldSaveElementsWhenDoesntExists() {
        UUID[] id = repository.create(message1, message2, message3, message4, message5, message6);
        Collection<Message> toInspect = repository.findAll();
        assertAll(
                () -> assertEquals(6, toInspect.size()), /// TODO: 05.06.2022 сделать массив сообщений и брать expected из .lenght
                () -> assertTrue(toInspect.contains(message3))
        );


    }

    @Test
    public void shouldFindElementsWhenUsedSeverity() {
        UUID[] id = repository.create(message1, message2, message3, message4, message5, message6);
        Message[] toInspect = repository.collectionToArray(repository.findBySeverity(MAJOR));
        int counter = 0;
        for (Message count : toInspect) {
            assertEquals(MAJOR, toInspect[counter].getLevel());
            counter++;
        }
    }

}







