package com.example.analytic_service.Controllers;


import com.example.analytic_service.Services.AnalyticService;
import com.example.analytic_service.entity.DTO.AnalyticResponse;
import com.example.analytic_service.entity.DTO.AnalyticsEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticController {

    private final AnalyticService analyticService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AnalyticsEventDto analyticsEventDto) {
        analyticService.saveEvent(analyticsEventDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<AnalyticResponse> findAll() {
        return ResponseEntity.ok(analyticService.generateAnalytics());
    }
}
