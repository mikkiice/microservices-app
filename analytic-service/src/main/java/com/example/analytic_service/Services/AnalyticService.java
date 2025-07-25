package com.example.analytic_service.Services;

import com.example.analytic_service.entity.DTO.AnalyticResponse;
import com.example.analytic_service.entity.DTO.AnalyticsEventDto;

public interface AnalyticService {
    AnalyticResponse generateAnalytics();
    void saveEvent(AnalyticsEventDto event);
}
