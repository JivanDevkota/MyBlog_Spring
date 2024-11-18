package com.nsu.thmeleafproject.service;

import com.nsu.thmeleafproject.model.Post;
import com.nsu.thmeleafproject.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    private final String imgDirectory = "D:/springboot/thmeleafProject/src/main/resources/static/img/";

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setViewCount(post.getViewCount()+1);
           postRepository.save(post);
           return post;
        }else {
            throw new EntityNotFoundException("Post not found");
        }
    }
    public void likePost(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setLikeCount(post.getLikeCount()+1);
            postRepository.save(post);
        }
        else {
            throw new EntityNotFoundException("Post not found with id: "+id);
        }
    }
    @Override
    public Post savePost(Post post, MultipartFile file)throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Create the full file path (absolute path)
        String filePath = imgDirectory + filename;

        // Create the destination file and ensure its directories exist
        File destFile = new File(filePath);
        destFile.getParentFile().mkdirs();

        // Transfer the uploaded file to the destination file
        file.transferTo(destFile);
        post.setImg("/img/" + filename);
        post.setDate(new Date());
        post.setLikeCount(0);
        post.setViewCount(0);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
