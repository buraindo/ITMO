package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class LogoutPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        HttpSession session = request.getSession();
        getEventService().markEvent(getUserService().find((Long) session.getAttribute(USER_ID_SESSION_KEY)), Event.Type.LOGOUT);
        session.removeAttribute(USER_ID_SESSION_KEY);
        throw new RedirectException("/index");
    }
}
