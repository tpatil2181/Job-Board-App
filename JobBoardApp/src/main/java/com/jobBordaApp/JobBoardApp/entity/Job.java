package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="job")
public class Job {
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	Integer jobId;
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employeer employer;
	@Column(name="job_title")
	String job_title;
	@Column(name="job_dis")
	String job_discription;
	@Column(name="status")
	String status;
	@Column(name="create_date")
	String create_date;
	
	
	
	
//	In jobCandidatecompany table add two more fields  applystatus and BookmarkStatus
	
	public Job() {
		// TODO Auto-generated constructor stub
	}
	

	public Job(Employeer employer, String job_title) {
	super();
	this.employer = employer;
	this.job_title = job_title;
}


//	public Job( Employeer employer, String job_title, String job_discripteion, String status, String create_date) {
//	super();
//	this.employer = employer;
//	this.job_title = job_title;
//	this.job_discription = job_discription;
//	this.status = status;
//	this.create_date = create_date;
//}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Employeer getEmployer() {
		return employer;
	}

	public void setEmployer(Employeer employer) {
		this.employer = employer;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getJob_discription() {
		return job_discription;
	}

	public void setJob_discription(String job_discription) {
		this.job_discription = job_discription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}



}
