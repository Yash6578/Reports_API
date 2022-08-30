package com.ashokit.reports_api.model;


import lombok.Data;


import java.time.LocalDate;

@Data
public class SearchRequest {
    String planName;
    String planStatus;
    LocalDate planStartDate;
    LocalDate planEndDate;
}
