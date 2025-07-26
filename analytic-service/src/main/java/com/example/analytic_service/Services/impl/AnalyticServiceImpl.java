package com.example.analytic_service.Services.impl;

import com.example.analytic_service.Repositories.AnalyticRepository;
import com.example.analytic_service.entity.AnalyticsEventEntity;
import com.example.analytic_service.entity.DTO.AnalyticResponse;
import com.example.analytic_service.Services.AnalyticService;
import com.example.analytic_service.entity.DTO.AnalyticsEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {

    private final AnalyticCalc analyticCalc;
    private final AnalyticRepository analyticRepository;

    @Override
    public AnalyticResponse generateAnalytics() {
        return analyticCalc.calc(analyticRepository.findAll());
    }

    @Override
    public void saveEvent(AnalyticsEventDto event) {
        AnalyticsEventEntity entity = new AnalyticsEventEntity();
        entity.setUserId(event.getUserId());
        entity.setPostId(event.getPostId());
        entity.setPostStatus(event.getPostStatus());
        entity.setReason(event.getReason());

        analyticRepository.save(entity);
    }





}
