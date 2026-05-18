package com.jobBordaApp.JobBoardApp.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.jobBordaApp.JobBoardApp.enums.JobStatus;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name="job")
public class Job {
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer jobId;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employeer employer;
	
	@Enumerated(EnumType.STRING)
	@Column(name="work_mode")
	private WorkMode workMode;
	
	@Column(name="location")
	private String jobLocation;
	
	@Column(name="min_experience")
	private Integer minExperience;
	
	@Column(name="max_experience")
	private Integer maxExperience;
	
	@Column(name="min_salary")
	private Integer minSalary;
	
	@Column(name="max_salary")
	private Integer maxSalary;
	
	@Column(name="openings")
	private Integer noOfOpenings;
	
	@Column(name="job_dis")
	private String jobDescription;
	
	@Column(name="role")
	private String role;
	
	@Column(name="employment_type")
	private String employmentType;
	
	@Column(name="industry_type")
	private String industryType;
	
	
	@Column(name="education")
	private String educations;
	
	
	@ManyToMany
	@JoinTable(
	    name = "job_skills",
	    joinColumns = @JoinColumn(name = "job_id"),
	    inverseJoinColumns = @JoinColumn(name = "skill_id")
	)
	private List<Skill> skills;
	
//	@Column(name="skills")
//	private String skills;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private JobStatus status;
	
	@Column(name="date_posted")
	private LocalDateTime datePosted;
	
	
	
	
}
