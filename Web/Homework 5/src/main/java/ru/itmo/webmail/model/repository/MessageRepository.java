package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface MessageRepository {

    List<Message> getMessages(User user) throws SQLException;
    void addMessage(Long sourceId, Long targetId, String text);

}
