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
public class CandidateLanguageDTO {
	Integer langId;
	private Integer candidateId; // reference to Candidate table
	private String language;
	private String proficiency;
}
