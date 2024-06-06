package com.rentme.app.resource;

import com.rentme.app.service.IAnalyticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@Tag(name = "analytics_events")
public class AnalyticsEventResource {

    private final IAnalyticsService analyticsService;

}
