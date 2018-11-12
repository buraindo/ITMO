package ru.itmo.webmail.web.page;

import ru.itmo.webmail.web.exception.RedirectException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class ConfirmPage extends Page {

    private void action(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        String secret = request.getParameter("secret");
        if (getUserService().confirm(secret)) {
            throw new RedirectException("/index", "confirmationDone");
        }
        throw new RedirectException("/index");
    }

}
