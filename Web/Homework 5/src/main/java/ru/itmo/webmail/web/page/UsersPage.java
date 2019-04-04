package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class UsersPage extends Page{
    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        view.put("users", getUserService().findAll());
    }
}
