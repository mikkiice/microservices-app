package com.example.post_service.Exceptions;

import com.example.post_service.Model.DTO.PostStatus;
import lombok.Getter;

@Getter
public class PostRejectException extends RuntimeException {

    private final String reason;
    private final PostStatus status;

    public PostRejectException(String reason, PostStatus status, String message) {
        super(message);
        this.reason = reason;
        this.status = status;
    }
}
