package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public class TalksPage extends Page {

    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private void send(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        try {
            if (getUserService().findByLogin(request.getParameter("target")) == null) {
                throw new ValidationException("User with such nickname not found.");
            }
        } catch (ValidationException e) {
            view.put("target", request.getParameter("target"));
            view.put("error", e.getMessage());
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getMessageService().addMessage(getUser().getId(), getUserService().findByLogin(request.getParameter("target")).getId(), request.getParameter("text"));
        action(request, view);
    }

    private void action(HttpServletRequest request, Map<String, Object> view) throws SQLException {
        view.put("messages", getMessageService().getMessages(getUser()));
    }

}
