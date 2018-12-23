package ru.itmo.wp7.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp7.domain.User;
import ru.itmo.wp7.exception.NoSuchResourceException;
import ru.itmo.wp7.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public void updatePassword(Long id, String password) {
        userRepository.updatePassword(id, password);
    }

    public User findById(long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchResourceException::new);
    }
}
