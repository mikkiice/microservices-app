package com.example.analytic_service.Services.impl;

import com.example.analytic_service.Config.MapperConfig;
import com.example.analytic_service.Repositories.AnalyticRepository;
import com.example.analytic_service.entity.AnalyticsEventEntity;
import com.example.analytic_service.entity.DTO.AnalyticResponse;
import com.example.analytic_service.Services.AnalyticService;
import com.example.analytic_service.entity.DTO.AnalyticsEventDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {

    private final ModelMapper modelMapper;
    private final AnalyticCalc analyticCalc;
    private AnalyticRepository analyticRepository;

    @Override
    public AnalyticResponse generateAnalytics() {
        return analyticCalc.calc(analyticRepository.findAll());
    }

    @Override
    public void saveEvent(AnalyticsEventDto event) {
        Optional.of(event)
                .map(d -> modelMapper.map(d, AnalyticsEventEntity.class))
                .ifPresent(analyticRepository::save);
    }




}
