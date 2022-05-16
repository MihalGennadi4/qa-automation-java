package com.tcs.edu.service;

import com.tcs.edu.domain.Message;

public abstract class ValidatedService {

    public boolean isArgsValid(Message... messages) {

            if (messages == null) return false;
            return true;


    }
}


