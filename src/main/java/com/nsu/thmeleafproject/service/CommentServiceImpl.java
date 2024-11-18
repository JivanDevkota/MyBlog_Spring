package com.nsu.thmeleafproject.service;

import com.nsu.thmeleafproject.model.Comment;
import com.nsu.thmeleafproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }

    @Override
    public Comment saveComment(Long postId,Comment comment) {
        return commentRepository.save(comment);
    }
}
