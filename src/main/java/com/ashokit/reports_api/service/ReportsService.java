package com.ashokit.reports_api.service;

import com.ashokit.reports_api.model.SearchRequest;
import com.ashokit.reports_api.model.SearchResponse;

import java.util.List;

public interface ReportsService {
    List<String> getUniquePlanNames();
    List<String> getUniquePlanStatuses();
    List<SearchResponse> search(SearchRequest searchRequest);
}
