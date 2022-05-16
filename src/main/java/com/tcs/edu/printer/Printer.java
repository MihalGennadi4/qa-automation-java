package com.tcs.edu.printer;

import com.tcs.edu.domain.Message;
import com.tcs.edu.service.Doubling;
import com.tcs.edu.service.MessageOrder;

public interface Printer {

    void print(Message... message);
    void print(Doubling doubling, Message... message);
    void print(MessageOrder orderBy, Message... message);
    void print(MessageOrder orderBy, Doubling doubling, Message... message);

}
