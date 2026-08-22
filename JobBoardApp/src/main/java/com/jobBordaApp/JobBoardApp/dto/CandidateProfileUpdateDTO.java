package com.jobBordaApp.JobBoardApp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jobBordaApp.JobBoardApp.entity.Skill;

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
public class CandidateProfileUpdateDTO {
	
	Integer candidateId;
	String firstName;
	String lastName;
	String candidateTitle;
	String candidateAbout;
	String mobNo;
	String contactEmail;
	Integer imageId;

}
