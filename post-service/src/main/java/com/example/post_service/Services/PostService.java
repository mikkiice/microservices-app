package com.example.post_service.Services;

import com.example.post_service.Model.DTO.PostRequest;
import com.example.post_service.Model.DTO.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest postRequest);
    List<PostResponse> getPostsByUser(Long userId);
}
