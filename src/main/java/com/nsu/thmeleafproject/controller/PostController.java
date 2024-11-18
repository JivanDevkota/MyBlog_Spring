package com.nsu.thmeleafproject.controller;

import com.nsu.thmeleafproject.model.Post;
import com.nsu.thmeleafproject.model.User;
import com.nsu.thmeleafproject.service.MyUser;
import com.nsu.thmeleafproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") Post post,
                             @RequestParam("file") MultipartFile file)
    throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser currentUser =(MyUser) authentication.getPrincipal();
        User user=currentUser.getUser();
        post.setUser(user);
        post.setPostedBy(currentUser.getUsername());
        String code = post.getCode();
        postService.savePost(post,file);
        return "redirect:/posts";  // Redirect back to the list of posts
    }

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "all_post";  // Ensure this template exists in your resources/templates directory
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "single_post";  // Ensure this template exists in your resources/templates directory
    }

    @PostMapping("/{id}/like")
    public String likePost(@PathVariable("id") Long postId) {
        postService.likePost(postId);
        return "redirect:/posts/" + postId;  // Redirect to the post page after liking
    }

    @DeleteMapping("/{id}")
    public String deletePostById(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

}
