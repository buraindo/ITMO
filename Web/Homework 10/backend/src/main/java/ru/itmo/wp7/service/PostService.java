package ru.itmo.wp7.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp7.domain.Post;
import ru.itmo.wp7.exception.NoSuchResourceException;
import ru.itmo.wp7.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void update(long id, String title, String text) {
        postRepository.update(id, title, text);
    }
}
