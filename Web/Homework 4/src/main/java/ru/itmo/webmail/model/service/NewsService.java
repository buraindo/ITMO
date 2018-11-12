package ru.itmo.webmail.model.service;

import com.google.common.base.Strings;
import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.NewsRepository;
import ru.itmo.webmail.model.repository.impl.NewsRepositoryImpl;

import java.util.List;

public class NewsService {

    private NewsRepository newsRepository = new NewsRepositoryImpl();

    public void add(News news) {
        newsRepository.save(news);
    }

    public void validateNews(String text) throws ValidationException {
        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Please, fill the area above");
        }
    }

    public User findById(Long id) { return newsRepository.findById(id); }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

}
