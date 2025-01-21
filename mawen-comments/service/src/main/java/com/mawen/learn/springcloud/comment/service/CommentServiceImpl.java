package com.mawen.learn.springcloud.comment.service;

import com.mawen.learn.springcloud.comment.api.CommentService;
import com.mawen.learn.springcloud.comment.bean.Comment;
import com.mawen.learn.springcloud.comment.exception.CommentNotFoundException;
import com.mawen.learn.springcloud.comment.mapper.CommentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Comment> findByPostId(Integer id) {
        return repository.findByPostId(id);
    }

    @Override
    @Transactional
    public Comment save(Comment post) {
        return repository.save(post);
    }

    @Override
    @Transactional
    public Comment update(Integer id, Comment comment) {
        Optional<Comment> existing = repository.findById(id);
        if(existing.isPresent()) {
            Comment updated = new Comment(
                    id,
                    comment.postId(),
                    comment.name(),
                    comment.email(),
                    comment.body(),
                    comment.version());
            return repository.save(updated);
        } else {
            throw new CommentNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
