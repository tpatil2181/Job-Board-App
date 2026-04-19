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
@Table(name="candidate_image")
public class CandidateImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candImgId;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;   // reference to Candidate table


	@Column(name="file_path")
    private String path;     // reference to Job table

	
	public CandidateImage() {
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	public CandidateImage(Candidate candidate, String path) {
		super();
		this.candidate = candidate;
		this.path = path;
	}







	public Integer getCandImgId() {
		return candImgId;
	}







	public void setCandImgId(Integer candImgId) {
		this.candImgId = candImgId;
	}







	public Candidate getCandidate() {
		return candidate;
	}







	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}







	public String getPath() {
		return path;
	}







	public void setPath(String path) {
		this.path = path;
	}








}
