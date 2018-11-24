package ru.itmo.webmail.model.repository.impl;

import javafx.util.Pair;
import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.domain.Util;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.service.EmailConfirmationService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();
    private final EmailConfirmationService emailConfirmationService = new EmailConfirmationService();

    @Override
    public User find(long userId) throws SQLException {
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE, "SELECT * FROM User WHERE id=?", "Can't find User by id and passwordSha.", Long.toString(userId));
        return result.getKey().next() ? toUser(result.getValue(), result.getKey()) : null;
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE, "SELECT * FROM User WHERE login=?", "Can't find User by id and passwordSha.", login);
        return result.getKey().next() ? toUser(result.getValue(), result.getKey()) : null;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE, "SELECT * FROM User WHERE email=?", "Can't find User by id and passwordSha.", email);
        return result.getKey().next() ? toUser(result.getValue(), result.getKey()) : null;
    }

    @Override
    public User findByLoginOfEmailAndPasswordSha(String loginOrEmail, String passwordSha) throws SQLException {
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE,"SELECT * FROM User WHERE email=? OR login=? AND passwordSha=?", "Can't find User by id and passwordSha.", loginOrEmail, loginOrEmail, passwordSha);
        return result.getKey().next() ? toUser(result.getValue(), result.getKey()) : null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE, "SELECT * FROM User ORDER BY id", "Can't find all users.");
        while (result.getKey().next()) {
            users.add(toUser(result.getValue(), result.getKey()));
        }
        return users;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private User toUser(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if ("id".equalsIgnoreCase(columnName)) {
                user.setId(resultSet.getLong(i));
            } else if ("email".equalsIgnoreCase(columnName)) {
                user.setEmail(resultSet.getString(i));
            } else if ("login".equalsIgnoreCase(columnName)) {
                user.setLogin(resultSet.getString(i));
            } else if ("passwordSha".equalsIgnoreCase(columnName)) {
                // No operations.
            } else if ("confirmed".equalsIgnoreCase(columnName)) {
                user.setConfirmed(resultSet.getInt(i) == 1);
            } else if ("creationTime".equalsIgnoreCase(columnName)) {
                user.setCreationTime(resultSet.getTimestamp(i));
            } else {
                throw new RepositoryException("Unexpected column 'User." + columnName + "'.");
            }
        }
        return user;
    }

    @Override
    public void save(User user, String passwordSha) {
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO User (login, passwordSha, email, confirmed, creationTime) VALUES (?, ?, ?, ?, NOW())", "Can't save User.", DatabaseUtils.QueryType.INSERT, user.getLogin(), passwordSha, user.getEmail(), "0");
    }

    @Override
    public boolean confirm(Long userId) {
        if (userId != null) {
            DatabaseUtils.process(DATA_SOURCE, "UPDATE User SET confirmed=1 WHERE id=?", "Can't confirm changes.", DatabaseUtils.QueryType.INSERT, Long.toString(userId));
            return true;
        }
        return false;
    }
}
