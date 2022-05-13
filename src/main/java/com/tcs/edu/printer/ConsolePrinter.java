package com.tcs.edu.printer;


import com.tcs.edu.domain.Message;
import com.tcs.edu.service.Doubling;
import com.tcs.edu.service.MessageOrder;

import java.util.Objects;

import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.Doubling.DOUBLES;
import static com.tcs.edu.service.MessageOrder.ASC;
import static com.tcs.edu.service.MessageOrder.DESC;
import static com.tcs.edu.service.MessageService.processMessage;

/**
 * Класс com.tcs.edu.printer.ConsolePrinter
 * Включает в себя методы связанные с выводом сообщений в консоль.
 */
public class ConsolePrinter {

    /**
     * Метод print
     * Предназначен для вывода сообщений в консоль.
     *
     * @param message - DTO содержащее сообщение и сопутствующую информацию
     * @author m.petrukhin
     */

    public static void print(Message... message) {
        for (Message current : message) {
            System.out.println(processMessage(current));
        }
    }


    /**
     * Print с возможностью изменения порядка вывода сообщений.
     *
     * @param orderBy  определяет возрастающий или убывающий порядок вывода.
     * @param messages сообщение (или несколько) для вывода в консоль.
     */

    public static void print(MessageOrder orderBy, Message... messages) {
        if (orderBy == ASC) {
            for (Message current : messages) {
                System.out.println(processMessage(current));
            }
        } else if (orderBy == DESC) {
            for (int counter = messages.length - 1; counter >= 0; counter--) {
                System.out.println(processMessage(messages[counter]));
            }
        }
    }


    /**
     * Print с возможностью убрать дубли из печати.
     *
     * @param doubling DISTINCT - убрать дубли, DOUBLES - оставить дубли.
     * @param messages сообщение (или несколько) для вывода в консоль.
     */
    public static void print(Doubling doubling, Message... messages) {
        if (doubling == DOUBLES) {
            for (Message current : messages) {
                System.out.println(processMessage(current));
            }
        } else if (doubling == DISTINCT) {
            Message[] output = new Message[messages.length];
            String[] toFind = new String[messages.length];
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
                if (found == false) {
                    output[count] = messages[count];

                    if (messages[count].getBody() != null) {
                        toFind[count] = messages[count].getBody();
                    }
                    order++;
                }
            }
            for (Message message : output) {
                System.out.println(processMessage(message));
            }
        }
    }
}










