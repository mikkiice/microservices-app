package com.example.analytic_service.Repositories;


import com.example.analytic_service.entity.AnalyticsEventEntity;
import com.example.analytic_service.entity.DTO.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticRepository extends JpaRepository<AnalyticsEventEntity, Long> {

    Long countByPostStatus(PostStatus postStatus);
    Long countByPostStatusAndReason(PostStatus postStatus, String reason);
    Long countByUserId(Long userId);
    Long countByUserIdAndPostStatus(Long userId, PostStatus postStatus);
    List<AnalyticsEventEntity> findByPostStatusAndReasonIsNotNull(PostStatus postStatus);
    List<AnalyticsEventEntity> findByUserId(Long userId);
}
