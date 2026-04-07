package com.jobBordaApp.JobBoardApp.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	Integer userId;
	@Column(name="first_name")
	String first_name;
	@Column(name="last_name")
	String last_name;
	@Column(name="mobile_no")
	String mobNo;
	@Column(name="email", unique=true)
	String email;
	@Column(name="password")
	String password;
	@Column(name="education")
	String education;
	@Column(name="skills")
	String skills;
	
//	@Column(name="address")
//	String address;
//	@Column(name="no_of_bookings")
//	Integer no_of_bookings=0;
//	@Column(name="user_bookings")
//	@OneToMany(targetEntity=Booking.class)
//	List<Booking> user_bookings;
	@Column(name="create_date")
	Date createdate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String first_name, String last_name, String mobNo, String email, String password) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobNo = mobNo;
		this.email = email;
		this.password = password;
	}



//	public User(Integer userId, String first_name, String last_name, String mobNo, String email, String password,
//			String education, String skills, Date createdate) {
//		super();
//		this.userId = userId;
//		this.first_name = first_name;
//		this.last_name = last_name;
//		this.mobNo = mobNo;
//		this.email = email;
//		this.password = password;
//		this.education = education;
//		this.skills = skills;
//		this.createdate = createdate;
//	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
	
	
}
