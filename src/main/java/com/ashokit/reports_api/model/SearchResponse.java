package com.ashokit.reports_api.model;

import lombok.Data;

@Data
public class SearchResponse {
    String name;
    String email;
    String mobileNumber;
    String ssn;
    String gender;
}
