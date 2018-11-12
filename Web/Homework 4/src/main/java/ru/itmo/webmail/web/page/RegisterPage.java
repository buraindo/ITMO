package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Map;

public class RegisterPage extends Page {
    private UserService userService = new UserService();

    private void action() {
        //no action
    }

    private void register(HttpServletRequest request, Map<String, Object> view) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String email = request.getParameter("email");
        user.setEmail(email);

        try {
            userService.validateRegistration(user, password, passwordConfirmation, email);
        } catch (ValidationException e) {
            view.put("login", user.getLogin());
            view.put("password", password);
            view.put("passwordConfirmation", passwordConfirmation);
            view.put("email", email);
            view.put("error", e.getMessage());
            return;
        }

        userService.register(user, email, password);
        throw new RedirectException("/index", "registrationDone");
    }
}
