package com.jobBordaApp.JobBoardApp.dto;

import com.jobBordaApp.JobBoardApp.enums.Months;

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
public class CandidateProjectDTO {
	
	    private Integer projectId;
	    private String projectTitle;
	    private String role;
//	    private String FieldOfStudy;
	    private Months smonth;
		private Integer syear;
		private Months  emonth;
		private Integer eyear;
		private Boolean isOngoing; 
	    private String discription;
	    private String casestudyurl;
	    private Integer candidateId;
	    
}
