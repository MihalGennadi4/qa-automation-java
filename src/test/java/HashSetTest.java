import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.repository.MessageRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.tcs.edu.decorator.SeverityLevel.MAJOR;
import static com.tcs.edu.decorator.SeverityLevel.MINOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@DisplayName("HashSetTest")
public class HashSetTest {
    MessageRepository repository = new HashMapMessageRepository();

    Message message1;
    Message message2;
    Message message3;
    Message message4;
    Message message5;
    Message message6;

    @BeforeEach
    public void setUp() {
        message1 = new Message(MAJOR, "Вывод 1");
        message2 = new Message(MINOR, "Вывод 2");
        message3 = new Message(MAJOR, "Вывод 3");
        message4 = new Message(MINOR, "Вывод 3");
        message5 = new Message(MINOR, "Вывод 2");
        message6 = new Message(MAJOR, "Вывод 4");

    }

    @Nested
    @DisplayName("SaveAndReadTest")
    class SaveAndReadTest {

        @Test
        @DisplayName("Save and read one element")
        public void shouldSaveElementWhenDoesntExists() {
            UUID[] id = repository.create(message1);
            Message[] toInspect = repository.findByPrimaryKey(id);
            assertThat(toInspect[0].getBody(), Matchers.equalTo(message1.getBody()));
            assertThat(toInspect[0].getLevel(), Matchers.equalTo(message1.getLevel()));
            assertThat(toInspect[0].getId(), Matchers.equalTo(message1.getId()));
        }

        @Test
        @DisplayName("Save and read doubling elements")
        public void shouldSaveElementWhenItExists() {
            repository.create(message1);
            repository.create(message1);
            Message[] toInspect = repository.collectionToArray(repository.findAll());
            assertThat(toInspect, is(arrayWithSize(2)));
        }

        @Test
        @DisplayName("Save and read all elements")
        public void shouldSaveElementsWhenDoesntExists() {
            UUID[] id = repository.create(message1, message2, message3, message4, message5, message6);
            Message[] toInspect = repository.collectionToArray(repository.findAll());
            int countId = id.length;
            assertThat(toInspect,  anyOf(is(arrayWithSize(countId)),arrayContaining(message1, message2, message3, message4, message5, message6)));
        }

        @Nested
        @DisplayName("OnlyReadTests")
        class OnlyReadTests {

            @Test
            @DisplayName("Read filtered elements")
            public void shouldFindElementsWhenUsedSeverity() {
                UUID[] id = repository.create(message1, message2, message3, message4, message5, message6);
                Message[] toInspect = repository.collectionToArray(repository.findBySeverity(MAJOR));
                int counter = 0;
                for (Message count : toInspect) {
                    assertThat("что-то пошло не так", toInspect[counter].getLevel(), Matchers.equalTo(MAJOR));
                    counter++;
                }

            }

        }
    }
}








