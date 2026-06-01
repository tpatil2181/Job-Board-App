package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Candidate candidate;   // reference to Candidate table

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;     // reference to Job table
    
    @ManyToOne
    @JoinColumn(name = "employeer_id")
    private Employeer employeerId;     // reference to Employeer table


    private String status;
    
//    
//    {
//    	  "Candidate": { "candidateId": 1 },
//    	  "job": { "jobId": 1 },
//    	  "employeerId": { "employeerId": 1 },
//    	  "status": "Applied"
//    	}



}
