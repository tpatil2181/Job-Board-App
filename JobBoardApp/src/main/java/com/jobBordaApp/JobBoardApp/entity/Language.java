package com.jobBordaApp.JobBoardApp.entity;

import com.jobBordaApp.JobBoardApp.enums.JobStatus;
import com.jobBordaApp.JobBoardApp.enums.LanguageProficiency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cndlang")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer langId;
    
    @Column(name="language")
    private String language;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "proficiency")
	private LanguageProficiency proficiency;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
//    @JsonIgnore
    private Candidate candidate;
}