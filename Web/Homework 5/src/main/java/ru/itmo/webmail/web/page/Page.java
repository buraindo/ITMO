package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.EmailConfirmationService;
import ru.itmo.webmail.model.service.EventService;
import ru.itmo.webmail.model.service.MessageService;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class Page {
    static final String USER_ID_SESSION_KEY = "userId";

    private final UserService userService = new UserService();
    private final MessageService messageService = new MessageService();
    private final EmailConfirmationService emailConfirmationService = new EmailConfirmationService();
    private final EventService eventService = new EventService();

    private User user;

    public User getUser() {
        return user;
    }

    UserService getUserService() {
        return userService;
    }

    MessageService getMessageService() {
        return messageService;
    }

    EmailConfirmationService getEmailConfirmationService() {
        return emailConfirmationService;
    }

    EventService getEventService() {
        return eventService;
    }

    public void before(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        Long userId = (Long) request.getSession().getAttribute(USER_ID_SESSION_KEY);
        if (userId != null) {
            user = userService.find(userId);
            view.put("user", user);
        }
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
