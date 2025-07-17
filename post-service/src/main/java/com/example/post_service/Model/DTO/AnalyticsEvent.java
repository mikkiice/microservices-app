package com.example.post_service.Model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class AnalyticsEvent {

    private Long userId;
    private Long postId;
    private PostStatus postStatus;
    private String reason;
    private LocalDateTime timestamp;
}
