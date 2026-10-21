package com.jobBordaApp.JobBoardApp.dto;

import com.jobBordaApp.JobBoardApp.enums.JobApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ChangeCandidateStatus {
	private Integer applyid;
	private JobApplicationStatus status;;
}
