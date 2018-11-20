package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.MessageRepository;
import ru.itmo.webmail.model.repository.impl.MessageRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class MessageService {

    private final MessageRepository messageRepository = new MessageRepositoryImpl();

    public List<Message> getMessages(User user) throws SQLException {
        return messageRepository.getMessages(user);
    }

    public void addMessage(Long sourceId, Long targetId, String text) {
        messageRepository.addMessage(sourceId, targetId, text);
    }

}
