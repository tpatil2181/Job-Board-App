package com.jobBordaApp.JobBoardApp.dto;

import com.jobBordaApp.JobBoardApp.enums.JobStatus;

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
public class ChangeJobStatusDTO {
	
	Integer jobId;
	JobStatus status;

}
