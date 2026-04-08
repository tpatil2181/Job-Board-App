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
@Table(name="candidate_resume")
public class CandidateResume {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;   // reference to Candidate table


	@Column(name="file_path")
    private String Path;     // reference to Job table

	
	public CandidateResume() {
		// TODO Auto-generated constructor stub
	}

	public CandidateResume( Candidate candidate, String path) {
		super();
		this.candidate = candidate;
		Path = path;
	}

	
	
	
	
	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}


}
