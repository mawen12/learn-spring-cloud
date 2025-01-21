package com.mawen.learn.springcloud.post.client;

import com.mawen.learn.springcloud.comment.bean.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "comments")
public interface CommentClient {

    @GetMapping("")
    List<Comment> findAll();

    @GetMapping("/{id}")
    Optional<Comment> findById(@PathVariable("id") Integer id);

    @GetMapping("/post/{id}")
    List<Comment> findByPostId(@PathVariable("id") Integer id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Comment save(Comment post);

    @PutMapping("{id}")
    Comment update(@PathVariable("id") Integer id, Comment post);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(Integer id);
}
