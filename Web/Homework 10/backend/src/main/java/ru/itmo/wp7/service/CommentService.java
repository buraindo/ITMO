package ru.itmo.wp7.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp7.domain.Comment;
import ru.itmo.wp7.domain.Post;
import ru.itmo.wp7.domain.User;
import ru.itmo.wp7.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void add(Comment comment, User user, Post post) {
        comment.setAuthor(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

}
