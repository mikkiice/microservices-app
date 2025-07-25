package com.example.analytic_service.entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnalyticsEventDto {
    private Long userId;
    private Long postId;
    private PostStatus postStatus;
    private String reason;
    private LocalDateTime timestamp;
}

