package com.jobBordaApp.JobBoardApp.entity;

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
import jakarta.persistence.Table;
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
@Table(name="candidate_project")
public class CandidateProject {
	
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer educationId;
	    
	    @Column(name="projTitle")
	    private String ProjectTitle;

	    @Column(name="role")
	    private String role;
	    
	    @Enumerated(EnumType.STRING)
		@Column(name="startmonth")
		Months smonth;
		
		@Column(name="startyear")
		Integer syear;
		
		@Enumerated(EnumType.STRING)
		@Column(name="endmonth")
		Months  emonth;
		
		@Column(name="endyear")
		Integer eyear;
		
		@Column(name="isongoing")
		Boolean isOngoing; 

	    @Column(name="disc")
	    private String discription;
	    
	    @Column(name="csurl")
	    private String casestudyurl;

	    @ManyToOne
	    @JoinColumn(name = "candidate_id")
	    private Candidate candidate;

}
