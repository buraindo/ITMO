package ru.itmo.webmail.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";

    private UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user, String password, String passwordConfirmation, String email) throws ValidationException {
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

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }
        if (!passwordConfirmation.equals(password)) {
            throw new ValidationException("Passwords do not match");
        }

        Pattern pattern = Pattern.compile("[a-z0-9]+@[a-z]+[.][a-z]+");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidationException("An email should look like this: example@inbox.com");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Email is already in use");
        }
    }

    public void validateLogin(String login, String password) throws ValidationException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new ValidationException("Login not found.");
        }
        String passwordSha1 = Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString();
        if (!user.getPasswordSha1().equals(passwordSha1)) {
            throw new ValidationException("Incorrect login or password.");
        }
    }

    public void register(User user, String email, String password) {
        user.setPasswordSha1(Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString());
        user.setId((long) (userRepository.findAll().size() + 1));
        userRepository.save(user);
    }

    public void login(HttpServletRequest request, String login) {
        User user = userRepository.findByLogin(login);
        request.getSession().setAttribute("user", user);
    }

    public User findById(Long id) { return userRepository.findById(id); }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int findCount() {return userRepository.findCount();}

}
