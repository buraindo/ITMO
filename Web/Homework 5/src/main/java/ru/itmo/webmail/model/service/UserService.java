package ru.itmo.webmail.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
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

    public void validateEnter(String loginOrEmail, String password) throws ValidationException, SQLException {
        boolean isEmail = false;
        if (loginOrEmail == null || loginOrEmail.isEmpty()) {
            throw new ValidationException("Login or email is required");
        }
        if (!loginOrEmail.matches("[a-z]+")) {
            if (loginOrEmail.matches(Util.EMAIL_PATTERN)) {
                isEmail = true;
            } else
                throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (loginOrEmail.length() > 8 && !isEmail) {
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

        if (userRepository.findByLoginOfEmailAndPasswordSha(loginOrEmail, getPasswordSha(password)) == null) {
            throw new ValidationException("Invalid login or password");
        }

    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString();
    }

    public User authorize(String loginOrEmail, String password) throws SQLException {
        return userRepository.findByLoginOfEmailAndPasswordSha(loginOrEmail, getPasswordSha(password));
    }

    public User find(long userId) throws SQLException {
        return userRepository.find(userId);
    }

    public boolean confirm(Long userId) throws SQLException {
        return userRepository.confirm(userId);
    }

    public User findByLogin(String login) throws SQLException {
        return userRepository.findByLogin(login);
    }
}
