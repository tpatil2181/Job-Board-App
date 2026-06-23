package com.jobBordaApp.JobBoardApp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;
import com.jobBordaApp.JobBoardApp.entity.Skill;
import com.jobBordaApp.JobBoardApp.enums.JobStatus;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class JobDTO {
	
	 private Integer jobId;

	    private String jobTitle;

	    private Integer employerId;

	    private String employerName;

	    private WorkMode workMode;

	    private String jobLocation;

	    private Integer minExperience;

	    private Integer maxExperience;

	    private Integer minSalary;

	    private Integer maxSalary;

	    private Integer noOfOpenings;

	    private String jobDescription;

	    private String role;

	    private String employmentType;

	    private String industryType;

	    private String educations;

//	    private List<Skill> skills;

	    private JobStatus status;

	    private LocalDateTime datePosted;

}
