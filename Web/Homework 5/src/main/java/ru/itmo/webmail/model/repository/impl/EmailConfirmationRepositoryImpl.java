package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.Util;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailConfirmationRepositoryImpl implements EmailConfirmationRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public Long getBySecret(String secret) throws SQLException {
        ResultSet resultSet = DatabaseUtils.process(DATA_SOURCE, "SELECT userId FROM EmailConfirmation WHERE secret=?", "Can't confirm changes.", DatabaseUtils.QueryType.FIND, secret);
        assert resultSet != null;
        if (resultSet.next()) {
            return resultSet.getLong(1);
        }
        return null;
    }

    @Override
    public void addRecord(long userId) {
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO EmailConfirmation (userId, secret, creationTime) VALUES (?, ?, NOW())", "Can't save User.", DatabaseUtils.QueryType.INSERT, Long.toString(userId), Util.randomWords[Math.abs(Util.random.nextInt()) % 1000]);
    }
}
