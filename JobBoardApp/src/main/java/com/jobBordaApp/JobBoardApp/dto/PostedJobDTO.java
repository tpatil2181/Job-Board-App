package com.jobBordaApp.JobBoardApp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jobBordaApp.JobBoardApp.entity.Skill;
import com.jobBordaApp.JobBoardApp.enums.JobStatus;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;

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
public class PostedJobDTO {
	
	private Integer jobId;
	private String jobTitle;
	private LocalDateTime datePosted;
	private Integer minExperience;
	private Integer maxExperience;
	private WorkMode workMode;
	private String jobLocation;
	private Integer noOfApplicants;
	private JobStatus status;
	
}
