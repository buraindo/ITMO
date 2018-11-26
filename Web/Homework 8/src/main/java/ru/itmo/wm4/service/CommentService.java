package ru.itmo.wm4.service;

import org.springframework.stereotype.Service;
import ru.itmo.wm4.domain.Comment;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.User;
import ru.itmo.wm4.repository.CommentRepository;

@Service
public class CommentService {

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private final CommentRepository commentRepository;

    public void save(Comment comment, User user, Notice notice) {
        comment.setUser(user);
        comment.setNotice(notice);
        commentRepository.save(comment);
    }

}
