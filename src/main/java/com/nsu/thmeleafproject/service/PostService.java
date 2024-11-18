package com.nsu.thmeleafproject.service;

import com.nsu.thmeleafproject.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> getAllPosts();
    Post getPostById(Long id);
    void likePost(Long id);
    Post savePost(Post post, MultipartFile file)throws IOException;
    void  deletePost(Long id);
}
