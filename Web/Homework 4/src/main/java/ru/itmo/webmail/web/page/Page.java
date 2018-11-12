package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

class Page {

    private UserService userService = new UserService();

    void before(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
        view.put("userService", userService);
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null) {
            User user = ((User) userObj);
            view.put("user", user);
        }
    }

    void after(HttpServletRequest request, Map<String, Object> view) {
        //no action
    }

}
