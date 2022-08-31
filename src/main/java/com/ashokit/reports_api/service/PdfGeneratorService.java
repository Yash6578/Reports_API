package com.ashokit.reports_api.service;

import com.ashokit.reports_api.model.SearchResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PdfGeneratorService {
    ByteArrayInputStream getPdfReport(List<SearchResponse> data);
}
