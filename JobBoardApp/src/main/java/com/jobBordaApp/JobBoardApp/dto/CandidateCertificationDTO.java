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
public class CandidateCertificationDTO {
	

    private Integer certificationId;

    private String certificateName;

    private Integer candidateId;

}
