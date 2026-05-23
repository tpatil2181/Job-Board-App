package com.jobBordaApp.JobBoardApp.dto;


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
    private String startYear;
    private String endYear;
    private Double percentage;
    private Integer candidateId;
    
}
