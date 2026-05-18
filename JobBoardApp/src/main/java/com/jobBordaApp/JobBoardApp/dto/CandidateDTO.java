package com.jobBordaApp.JobBoardApp.dto;

import java.util.Date;
import java.util.List;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;
import com.jobBordaApp.JobBoardApp.entity.CandidateExperience;
import com.jobBordaApp.JobBoardApp.entity.CandidateImage;
import com.jobBordaApp.JobBoardApp.entity.CandidateResume;
import com.jobBordaApp.JobBoardApp.entity.Skill;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class CandidateDTO {

	
	Integer candidateId;
	String firstName;
	String lastName;
	String candidateTitle;
	String candidateAbout;
	String mobNo;
	String email;	
	String password;
	List<CandidateEducationDTO> educations;
    List<CandidateExperienceDTO> experiences;
    List<CandidateCertificationDTO> certifications;
	CandidateResume resume;  // reference to Candidate table
	Integer ResumeId;
	CandidateImage imageId;
	List<Skill> skills;
	Boolean isActive;
	Date createdate;
	
	
}
