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
	
	
	
	

	
	
	public Job() {
		// TODO Auto-generated constructor stub
	}




	public Job(String jobTitle, Employeer employer, WorkMode workMode, String jobLocation, Integer minExperience,
			Integer maxExperience, Integer minSalary, Integer maxSalary, String jobDescription, String role,
			String employmentType, String industryType, String educations, List<Skill> skills) {
		super();
		this.jobTitle = jobTitle;
		this.employer = employer;
		this.workMode = workMode;
		this.jobLocation = jobLocation;
		this.minExperience = minExperience;
		this.maxExperience = maxExperience;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.jobDescription = jobDescription;
		this.role = role;
		this.employmentType = employmentType;
		this.industryType = industryType;
		this.educations = educations;
		this.skills = skills;
	}
	
	
	
	

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Employeer getEmployer() {
		return employer;
	}

	public void setEmployer(Employeer employer) {
		this.employer = employer;
	}

	public WorkMode getWorkMode() {
		return workMode;
	}

	public void setWorkMode(WorkMode workMode) {
		this.workMode = workMode;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public Integer getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(Integer minExperience) {
		this.minExperience = minExperience;
	}

	public Integer getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(Integer maxExperience) {
		this.maxExperience = maxExperience;
	}

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}
	
	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Integer getNoOfOpenings() {
		return noOfOpenings;
	}

	public void setNoOfOpenings(Integer noOfOpenings) {
		this.noOfOpenings = noOfOpenings;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	
	public String getEducations() {
		return educations;
	}

	public void setEducations(String educations) {
		this.educations = educations;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public LocalDateTime getDatePosted() {
		return datePosted;
	}
	
	public void setDatePosted(LocalDateTime datePosted) {
		this.datePosted = datePosted;
	}
	
	
	
	
	
	
	
//	In jobCandidatecompany table add two more fields  applystatus and BookmarkStatus
	
//	public Job() {
//		// TODO Auto-generated constructor stub
//	}
//	
//
//	public Job(Employeer employer, String job_title) {
//	super();
//	this.employer = employer;
//	this.jobTitle = job_title;
//}


//	show tables
//
//	select * from candidate
//
//	select * from employeer
//
//	select * from job
//
//	select * from apply_job
//
//	select * from candidate_resume


}
