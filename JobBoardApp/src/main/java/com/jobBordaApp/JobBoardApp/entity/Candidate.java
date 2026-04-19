package com.jobBordaApp.JobBoardApp.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="candidate")
public class Candidate {
	

	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	Integer candidateId;
	@Column(name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="cand_title")
	String candidateTitle;
	
	@Column(name="about")
	String candidateAbout;
	
	@Column(name="mobile_no")
	String mobNo;
	
	@Column(name="email", unique=true)
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="education")
	String education;
	
//	@Column(name="experience")
//	String CandidateExperience experience;
//	@Column(name="candidate_profile_image_id")
//	String CandidatProfileImage;
	@OneToOne
	@JoinColumn(name = "resume_id")
	private CandidateResume resume;  // reference to Candidate table
	
	@OneToOne
	@JoinColumn(name = "cand_image_id")
	private CandidateImage image;
	
	@ManyToMany
	@JoinTable(
	    name = "candidate_skills",
	    joinColumns = @JoinColumn(name = "candidateId"),
	    inverseJoinColumns = @JoinColumn(name = "skill_id")
	)
	private List<Skill> skills;
	
	@Column(name="is_active")
	Boolean isActive;
	
	@Column(name="create_date")
	Date createdate;
	
	public Candidate() {
		// TODO Auto-generated constructor stub
	}
	


	public Candidate(String firstName, String lastName, String mobNo, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobNo = mobNo;
		this.email = email;
		this.password = password;
	}



	public Integer getCandidateId() {
		return candidateId;
	}



	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getCandidateTitle() {
		return candidateTitle;
	}



	public void setCandidateTitle(String candidateTitle) {
		this.candidateTitle = candidateTitle;
	}



	public String getCandidateAbout() {
		return candidateAbout;
	}



	public void setCandidateAbout(String candidateAbout) {
		this.candidateAbout = candidateAbout;
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



	public CandidateResume getResume() {
		return resume;
	}



	public void setResume(CandidateResume resume) {
		this.resume = resume;
	}



	public CandidateImage getImage() {
		return image;
	}



	public void setImage(CandidateImage image) {
		this.image = image;
	}



	public List<Skill> getSkills() {
		return skills;
	}



	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}



	public Boolean getIsActive() {
		return isActive;
	}



	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}



	public Date getCreatedate() {
		return createdate;
	}



	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	

//	public Candidate(Integer userId, String first_name, String last_name, String mobNo, String email, String password,
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


	
	
	
}
