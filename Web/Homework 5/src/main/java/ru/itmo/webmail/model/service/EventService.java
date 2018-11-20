package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;

public class EventService {

    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void markEvent(User user, Event.Type action) {
        eventRepository.markEvent(user, action);
    }

}
