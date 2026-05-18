package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
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
    private String path;     // reference to Job table


	public CandidateResume(Candidate candidate, String path) {
		super();
		this.candidate = candidate;
		this.path = path;
	}
	
	
	

}
