package com.tcs.edu;

import com.tcs.edu.decorator.Severity;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.service.MessageService.processMessage;

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
        ConsolePrinter.print("Hello World!", Severity.MINOR);
        ConsolePrinter.print("Hello World!", Severity.REGULAR);
        ConsolePrinter.print("Hello World!", Severity.MAJOR);
        ConsolePrinter.print("Hello World!", Severity.REGULAR);
        ConsolePrinter.print("Hello World!", Severity.MAJOR);
        ConsolePrinter.print("Hello World!", Severity.MINOR);
    }
}