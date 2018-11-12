package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class AdminPage extends Page {

    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    private List<User> find(HttpServletRequest request, Map<String, Object> view) {
        return getUserService().findAll();
    }

    private void update(HttpServletRequest request, Map<String, Object> view) {
        long id = Long.parseLong(request.getParameter("id"));
        if (!((User)view.get("user")).isAdmin()) {
            return;
        }
        int type = request.getParameter("type").equals("Enable") ? 1 : 0;
        getUserService().update(id, type);
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
