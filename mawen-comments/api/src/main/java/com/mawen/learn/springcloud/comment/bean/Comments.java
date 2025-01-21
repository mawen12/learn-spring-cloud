package com.mawen.learn.springcloud.comment.bean;

import java.util.List;

public record Comments(
        List<Comment> comments
) {
}
