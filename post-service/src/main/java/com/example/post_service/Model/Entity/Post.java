package com.example.post_service.Model.Entity;


import com.example.post_service.Model.DTO.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Long userId;
    private String title;
    private String content;
    private PostStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
