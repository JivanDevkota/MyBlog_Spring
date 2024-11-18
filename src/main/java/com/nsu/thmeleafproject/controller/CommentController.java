package com.nsu.thmeleafproject.controller;

import com.nsu.thmeleafproject.model.Comment;
import com.nsu.thmeleafproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public String addComment(@RequestParam("postId") Long postId, @ModelAttribute Comment comment) {
        commentService.saveComment(postId,comment);
        return "redirect:/posts/" + postId;
    }
}
