package com.mawen.learn.springcloud.post.mapper;

import com.mawen.learn.springcloud.post.bean.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface PostRepository extends ListCrudRepository<Post, Integer> {

    Optional<Post> findByTitle(String title);
}
