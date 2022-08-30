package com.ashokit.reports_api.rest;

import com.ashokit.reports_api.model.SearchRequest;
import com.ashokit.reports_api.model.SearchResponse;
import com.ashokit.reports_api.service.ReportsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("unique/names")
    ResponseEntity<List<String>> getUniquePlanNames() {
        return ResponseEntity.ok().body(reportsService.getUniquePlanNames());
    }

    @GetMapping("unique/statuses")
    ResponseEntity<List<String>> getUniquePlanStatuses() {
        return ResponseEntity.ok().body(reportsService.getUniquePlanStatuses());
    }

    @PostMapping("/search")
    ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {
        return ResponseEntity.ok().body(reportsService.search(request));
    }
}
