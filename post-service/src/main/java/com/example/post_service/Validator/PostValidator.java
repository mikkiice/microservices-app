package com.example.post_service.Validator;


import com.example.post_service.Exceptions.PostRejectException;
import com.example.post_service.Feign.BadWordsClient;
import com.example.post_service.Model.DTO.PostRequest;
import com.example.post_service.Model.DTO.PostStatus;
import com.example.post_service.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostValidator {

    private final BadWordsClient badWordsClient;
    private final PostRepository postRepository;

    @Value("${post.max-length}")
    private int maxLength;

    public void validate(PostRequest postRequest) {

        String content = postRequest.getContent();
        if (content.length() > maxLength) {
            throw new PostRejectException("Content is too long", PostStatus.REJECTED, "length");
        }

        if(badWordsClient.exist(postRequest.getContent())) {
            throw new PostRejectException("Content exist bad words:" + postRequest.getContent(), PostStatus.REJECTED, "badWordsError");
        }

    }

    public void validateUserHasPosts(Long userId) {
        if(postRepository.findFirstByUserId(userId).isEmpty()) {
            throw new PostRejectException("User does not exist", PostStatus.REJECTED, "userIdError");
        }
    }
}
