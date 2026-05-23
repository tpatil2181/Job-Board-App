package com.jobBordaApp.JobBoardApp.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@Column(name="company_name")
	String companyName;
	@Column(name="job_title")
	String jobTitle;
	@Column(name="joining_date")
	Date joiningDate;
	@Column(name="ending_date")
	Date endingDate;
	@Column(name="is_current_company")
	Boolean isCurrentCompanny; 
	@Column(name="about_job")
	String aboutJobProfile;
	
}
