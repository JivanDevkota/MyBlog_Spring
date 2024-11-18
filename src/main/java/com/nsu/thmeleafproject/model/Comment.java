package com.nsu.thmeleafproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date createdAt;

//    private String postedBy;

    @ManyToOne
    @JoinColumn(name = "user_Id",nullable = false)
    private User postedBy;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}