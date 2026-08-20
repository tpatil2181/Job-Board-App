package com.jobBordaApp.JobBoardApp.entity;

import java.time.LocalDateTime;

import com.jobBordaApp.JobBoardApp.enums.JobApplicationStatus;
import com.jobBordaApp.JobBoardApp.enums.Months;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
public class ApplyJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyid;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;  

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;   
    
    @ManyToOne
    @JoinColumn(name = "employeer_id")
    private Employeer employeer;   
      
    @Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	JobApplicationStatus  status =JobApplicationStatus.APPLIED;;
	
	@Builder.Default
	@Column(name = "date_applied")
	private LocalDateTime dateApplied = LocalDateTime.now();
	
	
	
//  private Boolean isStatusApplied =true;


    
//  for withdraw use 0 to validate at frontend
//  @Builder.Default
//  private  String status = "Applied";
    
//    
//    {
//    	  "Candidate": { "candidateId": 1 },
//    	  "job": { "jobId": 1 },
//    	  "employeerId": { "employeerId": 1 },
//    	  "status": "Applied"
//    	}



}
