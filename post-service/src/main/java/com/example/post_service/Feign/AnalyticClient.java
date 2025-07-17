package com.example.post_service.Feign;


import com.example.post_service.Model.DTO.AnalyticsEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analytic-service")
public interface AnalyticClient {

    @PostMapping("/analytics")
    void sendEvent(@RequestBody AnalyticsEvent event);

}
