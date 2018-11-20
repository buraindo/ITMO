package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User find(long userId) throws SQLException;
    User findByLogin(String login) throws SQLException;
    User findByEmail(String email) throws SQLException;
    User findByLoginOfEmailAndPasswordSha(String loginOrEmail, String passwordSha) throws SQLException;
    List<User> findAll() throws SQLException;
    void save(User user, String passwordSha) throws SQLException;
    boolean confirm(String secret) throws SQLException;
}
