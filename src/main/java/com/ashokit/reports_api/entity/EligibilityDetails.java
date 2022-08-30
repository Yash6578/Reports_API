package com.ashokit.reports_api.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_details")
@Data
public class EligibilityDetails {
    @Id
    @GeneratedValue
    @Column(name = "eligibility_id")
    Long id;

    @Column(name = "person_name")
    String name;

    @Column(name = "person_email")
    String email;

    @Column(name = "person_mobile_number")
    String mobileNumber;

    @Column(name = "person_ssn")
    String ssn;

    @Column(name = "person_gender")
    String gender;

    @Column(name = "plan_name")
    String planName;

    @Column(name = "plan_status")
    String planStatus;

    @Column(name = "plan_start_date")
    LocalDate planStartDate;

    @Column(name = "plan_end_date")
    LocalDate planEndDate;

    @Column(name = "eligibility_details_created_by")
    String createdBy;

    @Column(name = "eligibility_details_updated_by")
    String updatedBy;

    @Column(name = "eligibility_details_created_date", updatable = false)
    @CreationTimestamp
    LocalDateTime createdDated;

    @Column(name = "eligibility_details_updated_date", insertable = false)
    @UpdateTimestamp
    LocalDateTime updatedDated;
}
