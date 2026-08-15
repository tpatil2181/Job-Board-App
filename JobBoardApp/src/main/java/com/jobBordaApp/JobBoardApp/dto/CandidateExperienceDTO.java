package com.jobBordaApp.JobBoardApp.dto;

import java.sql.Date;

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
public class CandidateExperienceDTO {

	Integer candExpId;
	private Integer candidateId; // reference to Candidate table
	String companyName;
	String jobTitle;
	String location;
	WorkMode workMode;
	Months smonth;
	Integer syear;
	Months  emonth;
	Integer eyear;
	Date joiningDate;
	Date endingDate;
	Boolean isCurrentCompanny; 
	String aboutJobProfile;


}
