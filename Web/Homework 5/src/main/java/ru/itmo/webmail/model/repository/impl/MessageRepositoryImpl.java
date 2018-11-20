package ru.itmo.webmail.model.repository.impl;

import javafx.util.Pair;
import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.MessageRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.service.UserService;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {

    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();
    private final UserService userService = new UserService();

    @SuppressWarnings("StatementWithEmptyBody")
    private Message toMessage(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        Message message = new Message();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if ("id".equalsIgnoreCase(columnName)) {
                //no action
            } else if ("sourceUserId".equalsIgnoreCase(columnName)) {
                message.setSource(userService.find(resultSet.getLong(i)).getLogin());
            } else if ("targetUserId".equalsIgnoreCase(columnName)) {
                message.setTarget(userService.find(resultSet.getLong(i)).getLogin());
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
