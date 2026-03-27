package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employeer")
public class Employeer {
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	int employeerId;
	@Column(name="employeer_name")
	String employeerName;
	@Column(name="website")
	String website;
	@Column(name="email")
	String email;
	@Column(name="contact")
	String contact;
	@Column(name="joblist")
	String joblist;
	
	
	public Employeer() {
		// TODO Auto-generated constructor stub
	}


	public Employeer(int employeerId, String employeerName, String website, String email, String contact,
			String joblist) {
		super();
		this.employeerId = employeerId;
		this.employeerName = employeerName;
		this.website = website;
		this.email = email;
		this.contact = contact;
		this.joblist = joblist;
	}


	public int getEmployeerId() {
		return employeerId;
	}


	public void setEmployeerId(int employeerId) {
		this.employeerId = employeerId;
	}


	public String getEmployeerName() {
		return employeerName;
	}


	public void setEmployeerName(String employeerName) {
		this.employeerName = employeerName;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getJoblist() {
		return joblist;
	}


	public void setJoblist(String joblist) {
		this.joblist = joblist;
	}
	
	
	
	
	
	
	

}
