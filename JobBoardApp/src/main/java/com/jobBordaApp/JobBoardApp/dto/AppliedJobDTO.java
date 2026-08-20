package com.jobBordaApp.JobBoardApp.dto;
import java.time.LocalDateTime;

import com.jobBordaApp.JobBoardApp.enums.JobApplicationStatus;

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
public class AppliedJobDTO {
	
	Integer applyid;
	String JobTitle;
	String Comapny;
	String WorkMode;
	LocalDateTime dateApplied;
	JobApplicationStatus status;
	
}
