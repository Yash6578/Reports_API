package com.ashokit.reports_api.service;

import com.ashokit.reports_api.model.SearchResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelGeneratorService {
    ByteArrayInputStream getExcelReport(List<SearchResponse> data);
}
