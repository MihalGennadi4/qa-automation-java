package com.tcs.edu.repository;

import com.tcs.edu.decorator.SeverityLevel;
import com.tcs.edu.domain.Message;

import javax.print.attribute.standard.Severity;
import java.util.Collection;
import java.util.UUID;

public interface MessageRepository {

    UUID UUID_create(Message message);

    UUID[] create(Message... message);

    Message[] findByPrimaryKey(UUID... key);

    Collection<Message> findAll();

    Collection<Message> findBySeverity(SeverityLevel by);

    Message[] collectionToArray(Collection input);

}
