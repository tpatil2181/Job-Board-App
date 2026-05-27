package com.jobBordaApp.JobBoardApp.entity;

import java.util.Date;
import java.util.List;

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
@Builder
@Entity
@Table(name="candidate_certification")
public class CandidateCertification {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer certificationId;

	    @Column(name="certifacte_name")
	    private String certificateName;
	    
	    @ManyToOne
	    @JoinColumn(name = "candidate_id")
	    private Candidate candidate;
	       
	    

}
