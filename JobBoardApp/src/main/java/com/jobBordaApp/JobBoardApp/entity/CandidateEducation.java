package com.jobBordaApp.JobBoardApp.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobBordaApp.JobBoardApp.enums.Months;

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
@Table(name="candidate_education")
public class CandidateEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationId;
    
    @Column(name="degree")
    private String degree;

    @Column(name="college")
    private String college;
    
    @Column(name="fieldOfStudy")
    private String FieldOfStudy;

//    @Column(name="start_yaer")
//    private String startYear;
//
//    @Column(name="end_year")
//    private String endYear;
    
    @Enumerated(EnumType.STRING)
	@Column(name="startmonth")
	Months smonth;
	
	@Column(name="startyear")
	Integer syear;
	
	@Enumerated(EnumType.STRING)
	@Column(name="endmonth")
	Months  emonth;
	
	@Column(name="endyear")
	Integer eyear;
	
	@Column(name="is_current_studying")
	Boolean isCurrentlystudying; 

    @Column(name="percentage")
    private Double percentage;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
//    @JsonIgnore
    private Candidate candidate;
}