package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void add(Article article);
    List<Article> findAll();
    List<Article> findAll(Long userId);
    void update(long id, int type);
}
