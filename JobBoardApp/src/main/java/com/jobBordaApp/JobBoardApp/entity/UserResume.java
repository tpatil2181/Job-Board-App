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
@Table(name="user_resume")
public class UserResume {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   // reference to User table


	@Column(name="file_path")
    private String Path;     // reference to Job table

	
	public UserResume() {
		// TODO Auto-generated constructor stub
	}

	public UserResume( User user, String path) {
		super();
		this.user = user;
		Path = path;
	}


	public Integer getUr_id() {
		return resumeId;
	}


	public void setUr_id(Integer resumeId) {
		this.resumeId = resumeId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getPath() {
		return Path;
	}


	public void setPath(String path) {
		Path = path;
	}

	
	

	
	
	
	
	
   

}
