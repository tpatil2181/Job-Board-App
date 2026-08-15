package com.jobBordaApp.JobBoardApp.entity;

import java.sql.Date;

import com.jobBordaApp.JobBoardApp.enums.Months;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="candidate_exp")
public class CandidateExperience {
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	Integer candExpId;
	@ManyToOne
    @JoinColumn(name = "candidate_id")
	private Candidate candidate;   // reference to Candidate table
	
	@Column(name="job_title")
	String jobTitle;

	@Column(name="company_name")
	String companyName;
	
	@Column(name="location")
	String location;
	
	@Enumerated(EnumType.STRING)
	@Column(name="workmode")
	private WorkMode workMode;
	
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
	
	@Column(name="is_current_company")
	Boolean isCurrentCompanny; 
	
	@Column(name="about_job")
	String aboutJobProfile;
	
	
//	@Column(name="joinMonth")
//	private String joinMonth;
	
//	@Column(name="joining_date")
//	Date joiningDate;
	
	
//	@Column(name="joining_date")
////	Date joiningDate;
//	@Column(name="ending_date")
//	Date endingDate;
	

	
	   
	    
//	    @Column(name="start_year")
//	    private String startYear;
//
//	    @Column(name="endMonth")
//	    private String endMonts;
//	    
//	    @Column(name="end_year")
//	    private String endYear;
//	    
//	    @Column(name="isPuercing")
//	    private Boolean isPuercing;
//
//	    @Column(name="percentage")
//	    private Double percentage;
	    
//	    @Column(name="disc")
//	    private Boolean Discription;

	
}
