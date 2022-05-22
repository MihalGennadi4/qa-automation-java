package com.tcs.edu.service;

import com.tcs.edu.domain.Message;

public abstract class ValidatedService {

    /**
     * Проверяем входные параметры
     *
     * @param messages
     */
    public void isArgsValid(Message... messages) {
        for (Message current : messages) {
            if (current == null) {
                throw new IllegalArgumentException("message потерялся и равен null");
            } else if (current.getBody() == null) {
                throw new NullPointerException("Получили null вместо body");
            } else if (current.getLevel() == null) {
                throw new NullPointerException("Получили null вместо severity");
            }


        }
    }
}


