package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.ArticleService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ArticlePage extends Page {

    private Map<String, Object> add(HttpServletRequest request, Map<String, Object> view) {
        Article article = new Article();
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        long userId = ((User) view.get("user")).getId();
        try {
            getArticleService().validateArticle(title, text);
        } catch (ValidationException e) {
            view.put("success", false);
            view.put("error", e.getMessage());
            return view;
        }

        article.setUserId(userId);
        article.setTitle(title);
        article.setText(text);
        getArticleService().add(article);
        view.put("success", true);
        return view;
    }

    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        //no action
    }

}
