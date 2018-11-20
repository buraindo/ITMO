package ru.itmo.webmail.model.repository;

import java.sql.SQLException;

public interface EmailConfirmationRepository {

    Long getBySecret(String secret) throws SQLException;
    void addRecord(long userId);

}
