package ru.itmo.webmail.model.repository.impl;

import javafx.util.Pair;
import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.domain.Util;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    public enum Event {
        ENTER,
        LOGOUT,
    }

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
    public User findByLoginAndPasswordSha(String login, String passwordSha, boolean isEmail) throws SQLException {
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE, isEmail ? "SELECT * FROM User WHERE email=? AND passwordSha=?" : "SELECT * FROM User WHERE login=? AND passwordSha=?", "Can't find User by id and passwordSha.", login, passwordSha);
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

    @SuppressWarnings("StatementWithEmptyBody")
    private Message toMessage(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        Message message = new Message();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if ("id".equalsIgnoreCase(columnName)) {
                //no action
            } else if ("sourceUserId".equalsIgnoreCase(columnName)) {
                message.setSource(find(resultSet.getLong(i)).getLogin());
            } else if ("targetUserId".equalsIgnoreCase(columnName)) {
                message.setTarget(find(resultSet.getLong(i)).getLogin());
            } else if ("text".equalsIgnoreCase(columnName)) {
                message.setText(resultSet.getString(i));
            } else if ("creationTime".equalsIgnoreCase(columnName)) {
                message.setCreationTime(resultSet.getTimestamp(i));
            } else {
                throw new RepositoryException("Unexpected column 'Message." + columnName + "'.");
            }
        }
        return message;
    }

    @Override
    public void save(User user, String passwordSha) throws SQLException {
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO User (login, passwordSha, email, confirmed, creationTime) VALUES (?, ?, ?, ?, NOW())", "Can't save User.", DatabaseUtils.QueryType.INSERT, user.getLogin(), passwordSha, user.getEmail(), "0");
        ResultSet resultSet = DatabaseUtils.process(DATA_SOURCE, "SELECT id FROM User WHERE email = ?", "Can't get Id of a user.", DatabaseUtils.QueryType.FIND, user.getEmail());
        assert resultSet != null;
        resultSet.next();
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO EmailConfirmation (userId, secret, creationTime) VALUES (?, ?, NOW())", "Can't save User.", DatabaseUtils.QueryType.INSERT, Long.toString(resultSet.getLong(1)), Util.randomWords[Math.abs(Util.random.nextInt()) % 1000]);
    }

    @Override
    public void markEvent(User user, Event action) {
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO Event (userId, type, creationTime) VALUES (?, ?, NOW())", "Can't save new message.", DatabaseUtils.QueryType.INSERT, Long.toString(user.getId()), action.name());
    }

    @Override
    public boolean confirm(String secret) throws SQLException {
        ResultSet resultSet = DatabaseUtils.process(DATA_SOURCE, "SELECT userId FROM EmailConfirmation WHERE secret=?", "Can't confirm changes.", DatabaseUtils.QueryType.FIND, secret);
        assert resultSet != null;
        if (resultSet.next()) {
            DatabaseUtils.process(DATA_SOURCE, "UPDATE User SET confirmed=1 WHERE id=?", "Can't confirm changes.", DatabaseUtils.QueryType.INSERT, Long.toString(resultSet.getLong(1)));
            return true;
        }
        return false;
    }

    @Override
    public List<Message> getMessages(User user) throws SQLException {
        List<Message> messages = new ArrayList<>();
        Pair<ResultSet, ResultSetMetaData> result = DatabaseUtils.process(DATA_SOURCE, "SELECT * FROM Talk WHERE sourceUserId = ? OR targetUserId = ? ORDER BY creationTime DESC", "Can't find all messages.", Long.toString(user.getId()), Long.toString(user.getId()));
        while (result.getKey().next()) {
            messages.add(toMessage(result.getValue(), result.getKey()));
        }
        return messages;
    }

    @Override
    public void addMessage(Long sourceId, Long targetId, String text) {
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO Talk (sourceUserId, targetUserId, text, creationTime) VALUES (?, ?, ?, NOW())", "Can't save new message.", DatabaseUtils.QueryType.INSERT, Long.toString(sourceId), Long.toString(targetId), text);
    }
}
