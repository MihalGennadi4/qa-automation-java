package com.tcs.edu.counter;

/**
 * Различные счетчики
 */
public class Counter {

    /**
     * Размер "страницы".
     */
    public static final int PAGE_SIZE = 2;
    /**
     * Перменная-счетчик с кол-вом вызовов метода addTimestamp.
     */
    private static int messageCount = 0;

    /**
     * Добавляет значение счетчика и возвращяет текущее значение
     */
    public static int messageCounter() {
        messageCount++;
        return messageCount;
    }

    /**
     * Метод вернет кол-во вызовов print, при условии что в аргумент был передан processMessage
     */
    public static int showMessageCount() {
        int count = messageCount;
        return count;
    }


}
