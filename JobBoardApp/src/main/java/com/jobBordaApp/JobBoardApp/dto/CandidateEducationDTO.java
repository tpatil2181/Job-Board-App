package com.jobBordaApp.JobBoardApp.dto;


import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.enums.Months;

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
public class CandidateEducationDTO {
	
    private Integer educationId;
    private String degree;
    private String college;
    private String fieldOfStudy;
    private Months smonth;
	private Integer syear;
	private Months  emonth;
	private Integer eyear;
	private Boolean isCurrentlystudying; 
    private String startYear;
    private String endYear;
    private Double percentage;
    private Integer candidateId;
    
}
