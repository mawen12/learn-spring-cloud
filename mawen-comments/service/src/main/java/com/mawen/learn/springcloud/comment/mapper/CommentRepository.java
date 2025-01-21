package com.mawen.learn.springcloud.comment.mapper;

import com.mawen.learn.springcloud.comment.bean.Comment;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CommentRepository extends ListCrudRepository<Comment, Integer> {

    List<Comment> findByPostId(Integer postId);
}
