package ru.itmo.webmail.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.Message;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.domain.Util;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class UserService {
    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";

    private UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user, String password) throws ValidationException, SQLException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (Strings.isNullOrEmpty(user.getEmail())) {
            throw new ValidationException("Email is required");
        }
        if (!user.getEmail().matches("[a-z0-9]+@[a-z0-9]+[.][a-z0-9]+")) {
            throw new ValidationException("Email must look like 'example@inbox.com'");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Email is already in use");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }
    }

    public void register(User user, String password) throws SQLException {
        String passwordSha = getPasswordSha(password);
        userRepository.save(user, passwordSha);
    }

    public List<User> findAll() throws SQLException {
        return userRepository.findAll();
    }

    public void validateEnter(String login, String password) throws ValidationException, SQLException {
        boolean isEmail = false;
        if (login == null || login.isEmpty()) {
            throw new ValidationException("Login or email is required");
        }
        if (!login.matches("[a-z]+")) {
            if (login.matches(Util.EMAIL_PATTERN)) {
                isEmail = true;
            } else
                throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (login.length() > 8 && !isEmail) {
            throw new ValidationException("Login can't be longer than 8");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }

        if (userRepository.findByLoginAndPasswordSha(login, getPasswordSha(password), isEmail) == null) {
            throw new ValidationException("Invalid login or password");
        }

    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString();
    }

    public User authorize(String login, String password) throws SQLException {
        return userRepository.findByLoginAndPasswordSha(login, getPasswordSha(password), login.matches(Util.EMAIL_PATTERN));
    }

    public User find(long userId) throws SQLException {
        return userRepository.find(userId);
    }

    public void markEvent(User user, UserRepositoryImpl.Event action) {
        userRepository.markEvent(user, action);
    }

    public boolean confirm(String secret) throws SQLException {
        return userRepository.confirm(secret);
    }

    public List<Message> getMessages(User user) throws SQLException {
        return userRepository.getMessages(user);
    }

    public void addMessage(Long sourceId, Long targetId, String text) {
        userRepository.addMessage(sourceId, targetId, text);
    }

    public User findByLogin(String login) throws SQLException {
        return userRepository.findByLogin(login);
    }
}
