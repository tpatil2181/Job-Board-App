package com.jobBordaApp.JobBoardApp.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateEducation> educations;
    
 // ✅ Experience
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateExperience> experiences;

    // ✅ Certification
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateCertification> certifications;

	

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
	
	
}
