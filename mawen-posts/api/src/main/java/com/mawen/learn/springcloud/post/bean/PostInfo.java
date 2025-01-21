package com.mawen.learn.springcloud.post.bean;

import com.mawen.learn.springcloud.comment.bean.Comment;

import java.util.List;

public record PostInfo(
        Post post,
        List<Comment> comments
) {
}
