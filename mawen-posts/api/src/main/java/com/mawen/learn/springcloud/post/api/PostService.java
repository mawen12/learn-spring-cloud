package com.mawen.learn.springcloud.post.api;

import com.mawen.learn.springcloud.post.bean.Post;
import com.mawen.learn.springcloud.post.bean.PostInfo;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAll();

    Optional<Post> findById(Integer id);

    PostInfo findInfoById(Integer id);

    Post save(Post post);

    Post update(Integer id, Post post);

    void deleteById(Integer id);


}
