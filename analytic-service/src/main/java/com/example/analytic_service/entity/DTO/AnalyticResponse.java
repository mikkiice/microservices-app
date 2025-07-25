package com.example.analytic_service.entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AnalyticResponse {
    private Long totalUsers;
    private Long totalPosts;
    private Long publishedPosts;
    private Long rejectedPosts;
    private Map<RejectionReason, Long> rejectedByReason;
    private String topBadWord;
    private Long topBadWordCount;

}
