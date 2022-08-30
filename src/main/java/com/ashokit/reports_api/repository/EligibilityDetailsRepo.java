package com.ashokit.reports_api.repository;

import com.ashokit.reports_api.entity.EligibilityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Long> {
    @Query("SELECT DISTINCT planStatus FROM EligibilityDetails ")
    List<String> findDistinctByPlanStatus();
    @Query("SELECT DISTINCT planName FROM EligibilityDetails")
    List<String> findDistinctByPlanName();
}
