package com.jobBordaApp.JobBoardApp.dto;


import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.enums.Months;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateCertificationDTO {
	

    private Integer certificationId;

    private String certificateName;
    
    private String issueingOrganization;
    
    private Months smonth;

    private Integer syear;
    
    private Months  emonth;
    
    private Integer eyear;
    
    private Integer certiid;

    private String certiurl;

    private Integer candidateId;

}
