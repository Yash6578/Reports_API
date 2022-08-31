package com.ashokit.reports_api.rest;

import com.ashokit.reports_api.model.SearchRequest;
import com.ashokit.reports_api.model.SearchResponse;
import com.ashokit.reports_api.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportsController {
    ReportsService reportsService;

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

    @GetMapping("/generate/excel")
    ResponseEntity<Resource> exportExcel() {
        String filename = "Enrolled Users Report.xls";
        InputStreamResource file = new InputStreamResource(reportsService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @GetMapping("/generate/pdf")
    ResponseEntity<Resource> exportPdf() {
        String filename = "Enrolled Users Report.pdf";
        InputStreamResource file = new InputStreamResource(reportsService.exportPdf());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE)).body(file);
    }
}
