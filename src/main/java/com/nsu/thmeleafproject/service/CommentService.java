package com.nsu.thmeleafproject.service;

import com.nsu.thmeleafproject.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentByPostId(Long id);
    Comment saveComment(Long postId,Comment comment);
}
