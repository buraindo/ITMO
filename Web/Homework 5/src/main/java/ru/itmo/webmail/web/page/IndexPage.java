package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        //
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void confirmationNotDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You haven't confirmed your email, consider doing it in order to authorize.");
    }

    private void confirmationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have successfully confirmed your email.");
    }
}
