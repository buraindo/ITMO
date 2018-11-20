package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.repository.EmailConfirmationRepository;
import ru.itmo.webmail.model.repository.impl.EmailConfirmationRepositoryImpl;

import java.sql.SQLException;

public class EmailConfirmationService {

    private final EmailConfirmationRepository emailConfirmationRepository = new EmailConfirmationRepositoryImpl();

    public Long getBySecret(String secret) throws SQLException {
        return emailConfirmationRepository.getBySecret(secret);
    }

    public void addRecord(long userId) {
        emailConfirmationRepository.addRecord(userId);
    }

}
