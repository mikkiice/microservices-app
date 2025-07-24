package com.example.post_service.Services.impl;

import com.example.post_service.Exceptions.PostRejectException;
import com.example.post_service.Feign.AnalyticClient;
import com.example.post_service.Model.DTO.AnalyticsEvent;
import com.example.post_service.Model.DTO.PostRequest;
import com.example.post_service.Model.DTO.PostResponse;
import com.example.post_service.Model.DTO.PostStatus;
import com.example.post_service.Model.Entity.Post;
import com.example.post_service.Repository.PostRepository;
import com.example.post_service.Services.PostService;
import com.example.post_service.Validator.PostValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AnalyticClient analyticClient;
    private final ModelMapper modelMapper;
    private final PostValidator postValidator;

    @Override
    public PostResponse createPost(PostRequest postRequest) {

        LocalDateTime now = LocalDateTime.now();
        try {
            postValidator.validate(postRequest);
        } catch (PostRejectException e) {
            Post rejectedPost = new Post();
            rejectedPost.setContent(postRequest.getContent());
            rejectedPost.setUserId(postRequest.getUserId());
            rejectedPost.setTitle(postRequest.getTitle());
            rejectedPost.setCreatedAt(now);
            rejectedPost.setUpdatedAt(now);
            rejectedPost.setStatus(PostStatus.REJECTED);

            Post savedRejectedPost = postRepository.save(rejectedPost);
            sendAnalytic(postRequest, savedRejectedPost.getId(), PostStatus.REJECTED, e.getReason());
            throw e;
        }

        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setUserId(postRequest.getUserId());
        post.setTitle(postRequest.getTitle());
        post.setCreatedAt(now);
        post.setStatus(PostStatus.PUBLISHED);
        post.setUpdatedAt(now);

        Post savedPost = postRepository.save(post);

        sendAnalytic(postRequest, savedPost.getId(), PostStatus.PUBLISHED, null);


        return modelMapper.map(savedPost, PostResponse.class);
    }

    @Override
    public List<PostResponse> getPostsByUser(Long userId) {
        postValidator.validateUserHasPosts(userId);
        return postRepository.findAllByUserId(userId)
                .stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .collect(Collectors.toList());
    }


    private void sendAnalytic(PostRequest postRequest, Long postId, PostStatus status, String reason) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent();
        analyticsEvent.setUserId(postRequest.getUserId());
        analyticsEvent.setPostId(postId);
        analyticsEvent.setPostStatus(status);
        analyticsEvent.setReason(reason);
        analyticsEvent.setTimestamp(LocalDateTime.now());

        try {
            analyticClient.sendEvent(analyticsEvent);
        } catch (Exception e) {
            log.warn("send analytics error: {}", e.getMessage());
        }

    }
}
