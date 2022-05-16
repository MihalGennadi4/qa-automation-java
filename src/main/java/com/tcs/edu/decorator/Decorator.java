package com.tcs.edu.decorator;


import com.tcs.edu.domain.Message;

public interface Decorator {
    Object addTimestamp (Message message);
    String severityDecorate(Message message);
}
