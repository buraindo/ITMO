package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.EventRepository;

import javax.sql.DataSource;

public class EventRepositoryImpl implements EventRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void markEvent(User user, Event.Type action) {
        DatabaseUtils.process(DATA_SOURCE, "INSERT INTO Event (userId, type, creationTime) VALUES (?, ?, NOW())", "Can't save new message.", DatabaseUtils.QueryType.INSERT, Long.toString(user.getId()), action.name());
    }

}
