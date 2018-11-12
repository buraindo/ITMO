package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.ArticleRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void add(Article article) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Article (userId, title, text, creationTime) VALUES (?, ?, ?, NOW())")) {
                statement.setLong(1, article.getUserId());
                statement.setString(2, article.getTitle());
                statement.setString(3, article.getText());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Article.", e);
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Article WHERE hidden = 0 ORDER BY creationTime DESC")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        articles.add(toArticle(statement.getMetaData(), resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find all articles.", e);
        }
        return articles;
    }

    @Override
    public List<Article> findAll(Long userId) {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Article WHERE userId = ? ORDER BY id")) {
                statement.setLong(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        articles.add(toArticle(statement.getMetaData(), resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find all articles.", e);
        }
        return articles;
    }

    @Override
    public void update(long id, int type) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Article SET hidden = ? WHERE id = ?")) {
                statement.setInt(1, type);
                statement.setLong(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't update hidden value.", e);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private Article toArticle(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if ("id".equalsIgnoreCase(columnName)) {
                article.setId(resultSet.getLong(i));
            } else if ("userId".equalsIgnoreCase(columnName)) {
                article.setUserId(resultSet.getLong(i));
            } else if ("title".equalsIgnoreCase(columnName)) {
                article.setTitle(resultSet.getString(i));
            } else if ("text".equalsIgnoreCase(columnName)) {
                article.setText(resultSet.getString(i));
            } else if ("hidden".equalsIgnoreCase(columnName)) {
                article.setHidden(resultSet.getInt(i) == 1);
            } else if ("creationTime".equalsIgnoreCase(columnName)) {
                article.setCreationTime(resultSet.getTimestamp(i));
            } else {
                throw new RepositoryException("Unexpected column 'Article." + columnName + "'.");
            }
        }
        return article;
    }
}
