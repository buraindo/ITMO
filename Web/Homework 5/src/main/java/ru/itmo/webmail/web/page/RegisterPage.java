package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class RegisterPage extends Page {
    private void register(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setEmail(request.getParameter("email"));
        String password = request.getParameter("password");

        try {
            getUserService().validateRegistration(user, password);
        } catch (ValidationException e) {
            view.put("login", user.getLogin());
            view.put("email", user.getEmail());
            view.put("password", password);
            view.put("error", e.getMessage());
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getUserService().register(user, password);
        user = getUserService().findByLogin(user.getLogin());
        getEmailConfirmationService().addRecord(user.getId());
        throw new RedirectException("/index", "registrationDone");
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
