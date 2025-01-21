package com.mawen.learn.springcloud.comment.api;

import com.mawen.learn.springcloud.comment.bean.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findAll();

    Optional<Comment> findById(Integer id);

    List<Comment> findByPostId(Integer id);

    Comment save(Comment post);

    Comment update(Integer id, Comment post);

    void deleteById(Integer id);
}

