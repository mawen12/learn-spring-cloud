package com.mawen.learn.springcloud.post.service;

import com.mawen.learn.springcloud.comment.api.CommentService;
import com.mawen.learn.springcloud.comment.bean.Comment;
import com.mawen.learn.springcloud.post.api.PostService;
import com.mawen.learn.springcloud.post.bean.Post;
import com.mawen.learn.springcloud.post.bean.PostInfo;
import com.mawen.learn.springcloud.post.client.CommentClient;
import com.mawen.learn.springcloud.post.exception.PostNotFoundException;
import com.mawen.learn.springcloud.post.mapper.PostRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CommentClient commentClient;

    public PostServiceImpl(PostRepository repository, CommentClient commentClient) {
        this.repository = repository;
        this.commentClient = commentClient;
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public PostInfo findInfoById(Integer id) {
        Optional<Post> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        List<Comment> comments = commentClient.findByPostId(id);
        return new PostInfo(optional.get(), comments);
    }

    @Override
    @Transactional
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    @Transactional
    public Post update(Integer id, Post post) {
        Optional<Post> existing = repository.findById(id);
        if (existing.isPresent()) {
            Post updatedPost = new Post(existing.get().id(),
                    existing.get().userId(),
                    post.title(),
                    post.body(),
                    existing.get().version());
            return repository.save(updatedPost);
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
