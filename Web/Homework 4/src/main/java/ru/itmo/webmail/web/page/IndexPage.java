package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

public class IndexPage extends Page{

    private NewsService newsService = new NewsService();

    private void action(Map<String, Object> view) {
        view.put("news", newsService.findAll().toArray());
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void loginDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "Welcome back, " + ((User)request.getSession().getAttribute("user")).getLogin());
    }

    private void logoutDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have logged out");
    }

    private void addDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have successfully added new article");
    }

}
