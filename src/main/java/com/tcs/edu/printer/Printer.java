package com.tcs.edu.printer;

import com.tcs.edu.domain.Message;
import com.tcs.edu.service.Doubling;
import com.tcs.edu.service.MessageOrder;
import com.tcs.edu.service.MessageService;

public interface Printer {

    void print(MessageService service, Message... message);

    void print(MessageService service,Doubling doubling, Message... message);

    void print(MessageService service,MessageOrder orderBy, Message... message);

    void print(MessageService service,MessageOrder orderBy, Doubling doubling, Message... message);

}
