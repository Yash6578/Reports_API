package com.ashokit.reports_api.service.impl;

import com.ashokit.reports_api.entity.EligibilityDetails;
import com.ashokit.reports_api.model.SearchRequest;
import com.ashokit.reports_api.model.SearchResponse;
import com.ashokit.reports_api.repository.EligibilityDetailsRepo;
import com.ashokit.reports_api.service.ReportsService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {
    final
    EligibilityDetailsRepo eligibilityDetailsRepo;

    public ReportsServiceImpl(EligibilityDetailsRepo eligibilityDetailsRepo) {
        this.eligibilityDetailsRepo = eligibilityDetailsRepo;
    }

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

        EligibilityDetails queryBuilder = new EligibilityDetails();

        if(searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals(""))
            queryBuilder.setPlanName(searchRequest.getPlanName());

        if(searchRequest.getPlanStatus() != null && !searchRequest.getPlanStatus().equals(""))
            queryBuilder.setPlanStatus(searchRequest.getPlanStatus());

        if(searchRequest.getPlanStartDate() != null)
            queryBuilder.setPlanStartDate(searchRequest.getPlanStartDate());

        if(searchRequest.getPlanEndDate() != null)
            queryBuilder.setPlanEndDate(searchRequest.getPlanEndDate());

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
}
