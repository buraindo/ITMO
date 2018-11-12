package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddNewsPage extends Page{

    private NewsService newsService = new NewsService();

    private void action(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            throw new RedirectException("/index");
        }
    }

    private void add(HttpServletRequest request, Map<String, Object> view) {
        User user = (User)request.getSession().getAttribute("user");
        Long id = user.getId();
        String text = request.getParameter("text");
        News news = new News();
        news.setUserId(id);
        news.setText(text);

        try {
            newsService.validateNews(text);
        } catch (ValidationException e) {
            view.put("text", text);
            view.put("error", e.getMessage());
            return;
        }

        newsService.add(news);
        throw new RedirectException("/index", "addDone");
    }

}
