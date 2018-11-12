package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.ArticleRepository;
import ru.itmo.webmail.model.repository.impl.ArticleRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ArticleService {

    private ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Title is required");
        }

        if (title.length() < 10) {
            throw new ValidationException("Title can't be shorter than 10");
        }

        if (text == null || text.isEmpty()) {
            throw new ValidationException("Text is required");
        }
        if (text.length() < 20) {
            throw new ValidationException("Text can't be shorter than 20");
        }
        if (text.length() > 1000) {
            throw new ValidationException("Text can't be longer than 1000");
        }
    }

    public void add(Article article) {
        articleRepository.add(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAll(Long userId) {
        return articleRepository.findAll(userId);
    }

    public void update(long id, int type) {
        articleRepository.update(id, type);
    }
}
