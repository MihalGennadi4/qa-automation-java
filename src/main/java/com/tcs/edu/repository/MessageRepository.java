package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;

import java.util.UUID;

public interface MessageRepository {

    UUID UUID_create(Message message);

    UUID[] create(Message... message);

    Message[] findByPrimaryKey(UUID... key);

}
