package ru.itmo.wp7.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.wp7.domain.Comment;
import ru.itmo.wp7.domain.Post;
import ru.itmo.wp7.domain.User;
import ru.itmo.wp7.exception.NoSuchResourceException;
import ru.itmo.wp7.service.CommentService;
import ru.itmo.wp7.service.PostService;
import ru.itmo.wp7.service.UserService;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1")
public class PostController extends ApiController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    public PostController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("posts")
    public List<Post> getPosts() {
        return postService.findAll();
    }

    @SuppressWarnings("Duplicates")
    @PostMapping("edit")
    public Map<String, Object> editPost(@RequestBody Map<String, String> postParameters) {
        boolean success = true;
        Map<String, Object> response = new HashMap<>();
        long id = Long.parseLong(postParameters.get("id"));
        String title = postParameters.get("title");
        String text = postParameters.get("text");
        Post post = postService.findById(id).orElse(null);
        if (post == null) {
            response.put("error", "No such post with following id: " + id);
            response.put("success", false);
            return response;
        }
        try {
            postService.update(id, title, text);
        } catch (ConstraintViolationException cve) {
            response.put("error", cve.getConstraintViolations().stream().map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
                    constraintViolation.getInvalidValue(), constraintViolation.getMessage())).collect(Collectors.toList()));
            success = false;
        }
        response.put("success", success);
        return response;
    }

    @SuppressWarnings(value = "Duplicates")
    @PostMapping("posts")
    public Map<String, Object> addPost(@RequestBody Map<String, String> postParameters) {
        boolean success = true;
        Map<String, Object> response = new HashMap<>();
        long userId = Long.parseLong(postParameters.get("userId"));
        String title = postParameters.get("title");
        String text = postParameters.get("text");
        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        post.setAuthor(userService.findById(userId));
        try {
            postService.addPost(post);
        } catch (ConstraintViolationException cve) {
            response.put("error", cve.getConstraintViolations().stream().map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
                    constraintViolation.getInvalidValue(), constraintViolation.getMessage())).collect(Collectors.toList()));
            success = false;
        }
        response.put("success", success);
        return response;
    }

    @GetMapping("post")
    public Post getPost(@RequestParam Long id) {
        return postService.findById(id).orElseThrow(NoSuchResourceException::new);
    }

    @GetMapping("comments")
    public List<Comment> getComments(@RequestParam Long postId) {
        Post post = postService.findById(postId).orElseThrow(NoSuchResourceException::new);
        return post.getComments();
    }

    @SuppressWarnings(value = "Duplicates")
    @PostMapping("post")
    public Map<String, Object> addComment(@RequestBody Map<String, String> commentParameters) {
        boolean success = true;
        Map<String, Object> response = new HashMap<>();
        long postId = Long.parseLong(commentParameters.get("postId"));
        long userId = Long.parseLong(commentParameters.get("userId"));
        String text = commentParameters.get("text");
        Post post = postService.findById(postId).orElse(null);
        User author = userService.findById(userId);
        Comment comment = new Comment();
        comment.setText(text);
        try {
            commentService.add(comment, author, post);
        } catch (ConstraintViolationException cve) {
            response.put("error", cve.getConstraintViolations().stream().map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
                    constraintViolation.getInvalidValue(), constraintViolation.getMessage())).collect(Collectors.toList()));
            success = false;
        }
        response.put("success", success);
        return response;
    }

}
