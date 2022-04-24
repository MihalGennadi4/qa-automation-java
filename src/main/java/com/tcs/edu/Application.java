package com.tcs.edu;

import com.tcs.edu.decorator.Severity;
import static com.tcs.edu.decorator.Severity.MAJOR;
import static com.tcs.edu.printer.ConsolePrinter.print;
import static com.tcs.edu.service.Doubling.DOUBLES;
import static com.tcs.edu.service.MessageOrder.ASC;
import static com.tcs.edu.service.MessageOrder.DESC;
import static com.tcs.edu.service.Doubling.DISTINCT;


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
        print(MAJOR, DISTINCT, "Первый", "Первый", "Второй", "Второй" , "Первый", "Шестой");
    }
}