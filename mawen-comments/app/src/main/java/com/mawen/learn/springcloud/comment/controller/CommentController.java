package com.mawen.learn.springcloud.comment.controller;

import com.mawen.learn.springcloud.comment.api.CommentService;
import com.mawen.learn.springcloud.comment.bean.Comment;
import com.mawen.learn.springcloud.comment.exception.CommentNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("")
    List<Comment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<Comment> findById(@PathVariable("id") Integer id) {
        log.info("Into findById method, argument is {}", id);
        return Optional.ofNullable(service.findById(id)).orElseThrow(CommentNotFoundException::new);
    }

    @GetMapping("/post/{postId}")
    List<Comment> findByPostId(@PathVariable("postId") Integer postId) {
        return service.findByPostId(postId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Comment save(@RequestBody @Valid Comment post) {
        return service.save(post);
    }

    @PutMapping("/{id}")
    Comment update(@PathVariable Integer id, @RequestBody Comment post) {
        return service.update(id, post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
