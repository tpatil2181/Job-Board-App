package com.jobBordaApp.JobBoardApp.dto;

import java.sql.Date;

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
public class CandidateExperienceDTO {

	Integer candExpId;
	private Integer candidateId; // reference to Candidate table
	String companyName;
	String jobTitle;
	Date joiningDate;
	Date endingDate;
	Boolean isCurrentCompanny; 
	String aboutJobProfile;

}
