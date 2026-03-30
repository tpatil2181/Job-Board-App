package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ApplyJob {
	
	
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   // reference to User table

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;     // reference to Job table
    
    @ManyToOne
    @JoinColumn(name = "employeer_id")
    private Employeer employeerId;     // reference to Employeer table


    private String status;
    
//    
//    {
//    	  "user": { "userId": 1 },
//    	  "job": { "jobId": 1 },
//    	  "employeerId": { "employeerId": 1 },
//    	  "status": "Applied"
//    	}

    
    public ApplyJob() {
		// TODO Auto-generated constructor stub
	}


	public ApplyJob(Integer id, User user, Job job, Employeer employeerId, String status) {
		super();
		this.applyid = id;
		this.user = user;
		this.job = job;
		this.employeerId = employeerId;
		this.status = status;
	}


	public Integer getId() {
		return applyid;
	}


	public void setId(Integer id) {
		this.applyid = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	public Employeer getEmployeerId() {
		return employeerId;
	}


	public void setEmployeerId(Employeer employeerId) {
		this.employeerId = employeerId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
    
    
	

}
