package com.mawen.learn.springcloud.post.controller;

import com.mawen.learn.springcloud.post.api.PostService;
import com.mawen.learn.springcloud.post.bean.Post;
import com.mawen.learn.springcloud.post.bean.PostInfo;
import com.mawen.learn.springcloud.post.exception.PostNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("")
    List<Post> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable("id") Integer id) {
        log.info("Into findById method, argument is {}", id);
        return Optional.ofNullable(service.findById(id)).orElseThrow(PostNotFoundException::new);
    }

    @GetMapping("/info/{id}")
    PostInfo findInfoById(@PathVariable("id") Integer id) {
        return service.findInfoById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Post save(@RequestBody @Valid Post post) {
        return service.save(post);
    }

    @PutMapping("/{id}")
    Post update(@PathVariable Integer id, @RequestBody Post post) {
        return service.update(id, post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
