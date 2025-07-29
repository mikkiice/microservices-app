package com.example.analytic_service.Services.impl;

import com.example.analytic_service.entity.AnalyticsEventEntity;
import com.example.analytic_service.entity.DTO.AnalyticResponse;
import com.example.analytic_service.entity.DTO.PostStatus;
import com.example.analytic_service.entity.DTO.RejectionReason;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AnalyticCalc {

    public AnalyticResponse calc(List<AnalyticsEventEntity> eventList) {
        long totalPosts = eventList.size();
        long totalPublishedPosts = countByStatus(eventList, PostStatus.PUBLISHED);
        long totalRejectedPosts = countByStatus(eventList, PostStatus.REJECTED);

        List<AnalyticsEventEntity> rejectedEventList = filterByStatus(eventList, PostStatus.REJECTED);
        Map<RejectionReason, Long> rejectionStats = groupByRejectionReason(rejectedEventList);
        Map.Entry<String, Long> topBadWordEntry = findTopBadWord(rejectedEventList);


        return buildResponse(
                totalPosts,
                totalPublishedPosts,
                totalRejectedPosts,
                rejectionStats,
                topBadWordEntry
        );

    }

    private long countByStatus(List<AnalyticsEventEntity> eventList, PostStatus postStatus) {
        return eventList.stream()
                .filter(e -> e.getPostStatus() != null && e.getPostStatus().equals(postStatus))
                .count();
    }

    private List<AnalyticsEventEntity> filterByStatus(List<AnalyticsEventEntity> eventList, PostStatus postStatus) {
        return eventList.stream()
                .filter(e -> e.getPostStatus() != null && e.getPostStatus().equals(postStatus))
                .toList();
    }

    private Map<RejectionReason, Long> groupByRejectionReason(List<AnalyticsEventEntity> rejectedEventList) {
        return rejectedEventList.stream()
                .map(this::mapToReasonEnum)
                .collect(Collectors.groupingBy(reason -> reason, Collectors.counting()));
    }

    private RejectionReason mapToReasonEnum(AnalyticsEventEntity rejectedEvent) {
        String reason = rejectedEvent.getReason();
        if ("length".equalsIgnoreCase(reason)) {
            return RejectionReason.LENGTH;
        }
        return RejectionReason.BAD_WORD;
    }

    private Map.Entry<String, Long> findTopBadWord(List<AnalyticsEventEntity> rejectedEventList) {
        return rejectedEventList.stream()
                .map(AnalyticsEventEntity::getReason)
                .filter(reason -> reason != null && reason.contains("Content exist bad words:"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

    private AnalyticResponse buildResponse(
            long totalPosts,
            long publishedPosts,
            long rejectedPosts,
            Map<RejectionReason, Long> groupByRejectionReason,
            Map.Entry<String, Long> topBadWordEntry
    ) {
        AnalyticResponse response = new AnalyticResponse();
        response.setTotalPosts(totalPosts);
        response.setPublishedPosts(publishedPosts);
        response.setRejectedPosts(rejectedPosts);
        response.setRejectedByReason(groupByRejectionReason);
        response.setTopBadWord(topBadWordEntry != null ? topBadWordEntry.getKey() : null);
        response.setTopBadWordCount(topBadWordEntry != null ? topBadWordEntry.getValue() : null);
        return response;
    }
}
