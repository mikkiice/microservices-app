package com.example.analytic_service.entity;

import com.example.analytic_service.entity.DTO.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "analytics_events")
public class AnalyticsEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long postId;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    private String reason;

    private LocalDateTime timestamp;
}
