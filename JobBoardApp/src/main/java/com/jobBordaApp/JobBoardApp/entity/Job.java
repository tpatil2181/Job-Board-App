package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job")
public class Job {
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	int jobId;
	@Column(name="job_title")
	String job_title;
	@Column(name="job_dis")
	String job_discription;
	@Column(name="employeerId")
	String employeerId;
	@Column(name="status")
	String status;
	@Column(name="create_date")
	String create_date;
//	@Column(name="first_name")
//	String first_name;
//	@Column(name="first_name")
//	String first_name;

	
	
//	In jobusercompany table add two more fields  applystatus and BookmarkStatus
	
	public Job() {
		// TODO Auto-generated constructor stub
	}
	
	



	public Job(int jobId, String job_title, String job_discription, String employeerId, String status,
			String create_date) {
		super();
		this.jobId = jobId;
		this.job_title = job_title;
		this.job_discription = job_discription;
		this.employeerId = employeerId;
		this.status = status;
		this.create_date = create_date;
	}





	public int getJobId() {
		return jobId;
	}



	public void setJobId(int jobId) {
		this.jobId = jobId;
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



	public String getEmployeerId() {
		return employeerId;
	}



	public void setEmployeerId(String employeerId) {
		this.employeerId = employeerId;
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
