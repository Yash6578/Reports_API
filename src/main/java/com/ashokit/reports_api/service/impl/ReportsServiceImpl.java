package com.ashokit.reports_api.service.impl;

import com.ashokit.reports_api.entity.EligibilityDetails;
import com.ashokit.reports_api.model.SearchRequest;
import com.ashokit.reports_api.model.SearchResponse;
import com.ashokit.reports_api.repository.EligibilityDetailsRepo;
import com.ashokit.reports_api.service.ExcelGeneratorService;
import com.ashokit.reports_api.service.PdfGeneratorService;
import com.ashokit.reports_api.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportsServiceImpl implements ReportsService {
    final
    EligibilityDetailsRepo eligibilityDetailsRepo;
    final
    ExcelGeneratorService excelGeneratorService;
    final
    PdfGeneratorService pdfGeneratorService;

    @Override
    public List<String> getUniquePlanNames() {
        return eligibilityDetailsRepo.findDistinctByPlanName();
    }

    @Override
    public List<String> getUniquePlanStatuses() {
        return eligibilityDetailsRepo.findDistinctByPlanStatus();
    }

    @Override
    public List<SearchResponse> search(SearchRequest searchRequest) {

        EligibilityDetails queryBuilder = getQueryBuilder(searchRequest);

        Example<EligibilityDetails> example = Example.of(queryBuilder);

        List<EligibilityDetails> eligibleList = eligibilityDetailsRepo.findAll(example);
        List<SearchResponse> responseList = new ArrayList<>();
        SearchResponse searchResponse = new SearchResponse();

        eligibleList.forEach(detail -> {
            BeanUtils.copyProperties(detail, searchResponse);
            responseList.add(searchResponse);
        });

        return responseList;
    }

    EligibilityDetails getQueryBuilder(SearchRequest searchRequest) {
        EligibilityDetails queryBuilder = new EligibilityDetails();

        if(searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals(""))
            queryBuilder.setPlanName(searchRequest.getPlanName());

        if(searchRequest.getPlanStatus() != null && !searchRequest.getPlanStatus().equals(""))
            queryBuilder.setPlanStatus(searchRequest.getPlanStatus());

        if(searchRequest.getPlanStartDate() != null)
            queryBuilder.setPlanStartDate(searchRequest.getPlanStartDate());

        if(searchRequest.getPlanEndDate() != null)
            queryBuilder.setPlanEndDate(searchRequest.getPlanEndDate());

        return queryBuilder;
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        return excelGeneratorService.getExcelReport(dataToExport());
    }

    @Override
    public ByteArrayInputStream exportPdf() {
        return pdfGeneratorService.getPdfReport(dataToExport());
    }

    List<SearchResponse> dataToExport() {
        List<EligibilityDetails> eligibleList = eligibilityDetailsRepo.findAll();

        List<SearchResponse> responseList = new ArrayList<>();
        SearchResponse searchResponse = new SearchResponse();

        eligibleList.forEach(detail -> {
            BeanUtils.copyProperties(detail, searchResponse);
            responseList.add(searchResponse);
        });

        return responseList;
    }
}
