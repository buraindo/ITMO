package ru.itmo.wp7.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.wp7.domain.User;
import ru.itmo.wp7.exception.ValidationException;
import ru.itmo.wp7.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1")
public class UserController extends ApiController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/authenticated")
    public User getAuthenticatedUser(User user) {
        return user;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @SuppressWarnings("Duplicates")
    @PostMapping("users")
    public Map<String, Object> register(@RequestBody Map<String, String> userCredentials) {
        boolean success = true;
        Map<String, Object> response = new HashMap<>();
        String login = userCredentials.get("login");
        String name = userCredentials.get("name");
        String password = userCredentials.get("password");
        User userWithGivenLogin = userService.findByLogin(login);
        if (userWithGivenLogin != null) {
            response.put("error", Collections.singletonList("Login is already in use"));
            response.put("success", false);
            return response;
        } else if (password.length() < 4) {
            response.put("error", Collections.singletonList("Password's length must be at least 4."));
            response.put("success", false);
            return response;
        }
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        try {
            userService.register(user);
            userService.updatePassword(user.getId(), password);
        } catch (ConstraintViolationException cve) {
            response.put("error", cve.getConstraintViolations().stream().map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
                    constraintViolation.getInvalidValue(), constraintViolation.getMessage())).collect(Collectors.toList()));
            success = false;
        }
        response.put("success", success);
        return response;
    }
}
