package com.jobBordaApp.JobBoardApp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.dto.AppliedJobDTO;
import com.jobBordaApp.JobBoardApp.dto.ApplyJobDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.entity.AppUser;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;
import com.jobBordaApp.JobBoardApp.entity.CandidateExperience;
import com.jobBordaApp.JobBoardApp.entity.CandidateResume;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.mapper.AppliedJobMapper;
import com.jobBordaApp.JobBoardApp.mapper.CandidateMapper;
import com.jobBordaApp.JobBoardApp.repository.AppUserRepo;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateCertificationsRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateEducationRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateExperienceRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateResumeReop;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepo candidateRepo;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private CandidateEducationRepo candidateEducationRepo;
	
	@Autowired
	private CandidateCertificationsRepo candidateCertificationRepo;
	
	@Autowired
	private CandidateExperienceRepo candidateExperienceRepo;
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private AppUserRepo appUserRepo;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	@Autowired
	private CandidateResumeReop candidateResumeRepo;
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private AppliedJobMapper appliedJobMapper;
	
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); 
	
	@Autowired
	private FileService fileService;
	
	
	
//---------------------------------------------Candidate-----------------------------------------------------------	
	

//	 Complete Method jwt Rolebased Autharization 
	
	
	public ResponseEntity<?> getCandidateProfile(Authentication authentication ) {
			String email = authentication.getName();

			AppUser user = appUserRepo.findByEmail(email);

		    Optional<Candidate> candidate = candidateRepo.findByUser(user);
		    
			if(candidate.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
			}
			Candidate cand=candidate.get();
			
		    CandidateDTO candidateDTO =candidateMapper.mapCandidateToCandidateDTO(cand);
		  
		    return ResponseEntity.ok(candidateDTO);
		    
	}

	
	public ResponseEntity<?> updateCandidate(@RequestBody Candidate updatedCandidate, Authentication authentication) {

	    String email = authentication.getName();

	    AppUser user = appUserRepo.findByEmail(email);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "User not found"));
	    }

	    Candidate existingCandidate = candidateRepo.findByUser(user)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Candidate not found"));

	    if (updatedCandidate.getFirstName() != null)
	        existingCandidate.setFirstName(updatedCandidate.getFirstName());

	    if (updatedCandidate.getLastName() != null)
	        existingCandidate.setLastName(updatedCandidate.getLastName());

	    if (updatedCandidate.getCandidateTitle() != null)
	        existingCandidate.setCandidateTitle(updatedCandidate.getCandidateTitle());

	    if (updatedCandidate.getCandidateAbout() != null)
	        existingCandidate.setCandidateAbout(updatedCandidate.getCandidateAbout());

	    if (updatedCandidate.getMobNo() != null)
	        existingCandidate.setMobNo(updatedCandidate.getMobNo());

	    if (updatedCandidate.getSkills() != null)
	        existingCandidate.setSkills(updatedCandidate.getSkills());

	    Candidate savedCandidate = candidateRepo.save(existingCandidate);

	    return ResponseEntity.ok(Map.of(
	            "message", "Candidate updated successfully",
	            "candidateId", savedCandidate.getCandidateId()
	    ));
	    
	}
	
	
	public ResponseEntity<?> deleteCandidate(Authentication authentication) {

	    String email = authentication.getName();

	    AppUser user = appUserRepo.findByEmail(email);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "User not found"));
	    }

	    Candidate candidate = candidateRepo.findByUser(user)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Candidate not found"));

	    candidateRepo.delete(candidate);

	    // Optional: delete login account too
	    appUserRepo.delete(user);

	    return ResponseEntity.ok(
	            Map.of("message", "Candidate deleted successfully"));
	}
	
	
	public ResponseEntity<?> changeCandidatePassword(@RequestBody ChangePasswordDTO newPass, Authentication authentication) {

	    String email = authentication.getName();

	    AppUser user = appUserRepo.findByEmail(email);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "User not found"));
	    }

	    // Verify current password
	    if (!passwordEncoder.matches(newPass.getCurrentPass(), user.getPassword())) {
	        return ResponseEntity.badRequest()
	                .body(Map.of("message", "Current password is incorrect"));
	    }

	    // Check if new password is same as current password
	    if (passwordEncoder.matches(newPass.getNewPass(), user.getPassword())) {
	        return ResponseEntity.badRequest()
	                .body(Map.of("message", "New password cannot be same as current password"));
	    }

	    // Update password
	    user.setPassword(passwordEncoder.encode(newPass.getNewPass()));
	    appUserRepo.save(user);

	    return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
	}
	
	
//---------------------------------------------Candidate Education----------------------------------------------	
	
		public ResponseEntity<?> addEducation( @RequestBody CandidateEducation education,Authentication authentication){
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
			
				Candidate Cnd=candidate.get();
				education.setCandidate(Cnd);
			    candidateEducationRepo.save(education);
			    return ResponseEntity.ok("Education Added");
		}
		
		public ResponseEntity<?> getEducation(@PathVariable Integer candidateId, @PathVariable Integer EduId,Authentication authentication){
				
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				
				Optional<CandidateEducation> CndEdu=candidateEducationRepo.findById(EduId);
				if(CndEdu.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Education Not Found"));
				}
				CandidateEducation CE=CndEdu.get();			
	
				return ResponseEntity.ok(CE);		
			
		}
		
		public ResponseEntity<?> updateEducation(@RequestBody CandidateEducation updatedEducation,Authentication authentication){
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				Candidate cnd=candidate.get();

			    CandidateEducation education = candidateEducationRepo.findById(updatedEducation.getEducationId()).orElseThrow(() -> new RuntimeException("Education not found"));

			    // Validation
			    if (!education.getCandidate().getCandidateId().equals(cnd.getCandidateId())) {
			        return ResponseEntity.badRequest().body("Education does not belong to this candidate");
			    }

			    education.setDegree(updatedEducation.getDegree());
			    education.setCollege(updatedEducation.getCollege());
			    education.setStartYear(updatedEducation.getStartYear());
			    education.setEndYear(updatedEducation.getEndYear());
			    education.setPercentage(updatedEducation.getPercentage());

			    candidateEducationRepo.save(education);

			    return ResponseEntity.ok("Education Updated Successfully");
		}
		
	
		public ResponseEntity<?> deleteEducation( @PathVariable Integer candidateId,
												  @PathVariable Integer educationId,
												  Authentication authentication) {
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				
				Candidate cnd=candidate.get();
	
			    CandidateEducation education = candidateEducationRepo.findById(educationId)
			            .orElseThrow(() -> new RuntimeException("Education not found"));
	
			    cnd.getEducations().remove(education);
	
			    candidateRepo.save(cnd);
	
			    return ResponseEntity.ok("Education Deleted Successfully");
		}
		
		   
//---------------------------------------------Candidate Certification----------------------------------------------	
		
		public ResponseEntity<?> addCandCertification( @PathVariable Integer candidateId,
													   @RequestBody CandidateCertification certification,
													   Authentication authentication) {
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
		
				Candidate Cnd=candidate.get();

			    certification.setCandidate(Cnd);
			    candidateCertificationRepo.save(certification);
			    return ResponseEntity.ok("Certification Added");
		}
		
		public ResponseEntity<?> getCertification(@PathVariable Integer candidateId,
												  @PathVariable Integer CertificationId,
												  Authentication authentication){
				
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
					
				Optional<CandidateCertification> CndCer=candidateCertificationRepo.findById(CertificationId);
				if(CndCer.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Education Not Found"));
				}
				CandidateCertification CC=CndCer.get();			
	
				return ResponseEntity.ok(CC);		
			
		}
		
		public ResponseEntity<?> updateCertification(@PathVariable Integer candidateId,
													 @RequestBody CandidateCertification updatedCedrtification,
													 Authentication authentication){
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				
				CandidateCertification certification = candidateCertificationRepo.findById(updatedCedrtification.getCertificationId()).orElseThrow(() -> new RuntimeException("Education not found"));

			    // Validation
			    if (!certification.getCandidate().getCandidateId().equals(candidateId)) {
			        return ResponseEntity.badRequest().body("Education does not belong to this candidate");
			    }

			    certification.setCertificateName(updatedCedrtification.getCertificateName());
//			    certification.setCollege(updatedCedrtification.ge());
//			    certification.setStartYear(updatedEducation.getStartYear());
	

			    candidateCertificationRepo.save(certification);

			    return ResponseEntity.ok("Certification Updated Successfully");
		}
		
		public ResponseEntity<?> deleteCertification( @PathVariable Integer candidateId,
													  @PathVariable Integer certificationId,
													  Authentication authentication) {
				
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
		
				Candidate Cnd=candidate.get();
	
			    CandidateCertification CandidateCertification = candidateCertificationRepo.findById(certificationId)
			            .orElseThrow(() -> new RuntimeException("Education not found"));
	
			    Cnd.getCertifications().remove(CandidateCertification);
	
			    candidateRepo.save(Cnd);
	
			    return ResponseEntity.ok("Certification Deleted Successfully");
		}
	
	
//---------------------------------------------Candidate Experience----------------------------------------------
		
		
		public ResponseEntity<?> addExperience( @PathVariable Integer candidateId,
												@RequestBody CandidateExperience experience,
												Authentication authentication) {
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
		
				Candidate Cnd=candidate.get();
			
			    experience.setCandidate(Cnd);
			    candidateExperienceRepo.save(experience);
			    return ResponseEntity.ok("Experience Added");
		}
		
		public ResponseEntity<?> getExperience(@PathVariable Integer candidateId,
											   @PathVariable Integer CertificationId,
											   Authentication authentication){
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
			
				Optional<CandidateCertification> CndCer=candidateCertificationRepo.findById(CertificationId);
				if(CndCer.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Education Not Found"));
				}
				CandidateCertification CC=CndCer.get();			
	
				return ResponseEntity.ok(CC);		
		
		}
		
		public ResponseEntity<?> updateExperience(@PathVariable Integer candidateId,
												  @RequestBody CandidateExperience updatedExperience,
												  Authentication authentication){
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
	
		
			 	CandidateExperience exp = candidateExperienceRepo.findById(updatedExperience.getCandExpId()).orElseThrow(() -> new RuntimeException("Education not found"));

			    // Validation
			    if (!exp.getCandidate().getCandidateId().equals(candidateId)) {
			        return ResponseEntity.badRequest().body("Experience does not belong to this candidate");
			    }

			    exp.setCompanyName(updatedExperience.getCompanyName());
			    exp.setJobTitle(updatedExperience.getJobTitle());
			    exp.setJoiningDate(updatedExperience.getJoiningDate());
			    exp.setEndingDate(updatedExperience.getEndingDate());
			    exp.setIsCurrentCompanny(updatedExperience.getIsCurrentCompanny());
			    exp.setAboutJobProfile(updatedExperience.getAboutJobProfile());

			    candidateExperienceRepo.save(exp);

			    return ResponseEntity.ok("Expericence Updated Successfully");
		}
		
		public ResponseEntity<?> deleteExperience( @PathVariable Integer candidateId,
												   @PathVariable Integer experienceId,
												   Authentication authentication) {
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
		
				Candidate Cnd=candidate.get();
			
			    CandidateExperience candExp = candidateExperienceRepo.findById(experienceId)
			            .orElseThrow(() -> new RuntimeException("Experience not found"));
	
			    Cnd.getExperiences().remove(candExp);
	
			    candidateRepo.save(Cnd);
	
			    return ResponseEntity.ok("Certification Deleted Successfully");
		}
		
		
//---------------------------------------------Candidate Resume----------------------------------------------
	
		public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file, @PathVariable Integer candidateId,Authentication authentication) {
			
			String email = authentication.getName();
			
			AppUser user = appUserRepo.findByEmail(email);

		    Optional<Candidate> candidate = candidateRepo.findByUser(user);
		    
			if(candidate.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
			}
			try {
	            int resumeId = fileService.uploadOrUpdateResume(candidateId,file);
	            Candidate cnd=candidateRepo.findById(candidateId).orElseThrow(()-> new ResourceNotFoundException("Candidate Not found with this ID"));
	            CandidateResume CR=candidateResumeRepo.findById(resumeId).orElseThrow(()-> new ResourceNotFoundException("Resume Not found with this ID"));;
	            cnd.setResume(CR);
	            candidateRepo.save(cnd);
	            return ResponseEntity.ok(Map.of("message", "Resume uploaded successfully at:" + CR.getPath()));
		
		        } catch (Exception e) {
		            return ResponseEntity.badRequest().body(Map.of(
						    "message",  "File upload failed: " + e.getMessage()
						));
		        }
		}
		
		
		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer resumeId) throws IOException{
			
				return fileService.getResume(resumeId);
			
		}
		
//---------------------------------------------Candidate Job----------------------------------------------	
		
		public ResponseEntity<?> jobApplication( @RequestBody ApplyJobDTO jobApplication,Authentication authentication ) {
			
				String email = authentication.getName();
	
		 		AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				Candidate cnd=candidate.get();
				Optional<Employeer> emp= employeerRepo.findById(jobApplication.getEmployeerId()); 
				
				if(emp.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Employeer not found"));						
				}
				
				Employeer empr=emp.get();				
			
				Optional<Job> job= jobRepo.findById(jobApplication.getJobId()); 
				
				if(job.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job not found"));						
				}
				
				Job jb=job.get();
			
				Optional<ApplyJob> AppliedJob=applyJobRepo.getJobApplicationByEmpJobCndId(
																			jobApplication.getCandidateId(), jobApplication.getJobId(),
																			jobApplication.getEmployeerId());
				if(AppliedJob.isPresent()) {
					
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","You have already applied for the job"));						
					}
				
				ApplyJob aj=new ApplyJob();
				
				aj.setCandidate(cnd);
				aj.setEmployeer(empr);
				aj.setJob(jb);
				
				applyJobRepo.save(aj);
				return ResponseEntity.ok(
					    Map.of("message", "Job Applied")
					);
//			    return ResponseEntity.ok("Job Applied");
		}
			
		
		
		public ResponseEntity<?> allAppliedJobs(Authentication authentication){
			
				String email = authentication.getName();
				
				AppUser user = appUserRepo.findByEmail(email);
	
			    Optional<Candidate> candidate = candidateRepo.findByUser(user);
			    
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				
				Candidate cnd=candidate.get();
				
				List<ApplyJob> allAppliedJobs=applyJobRepo.findAllApplicationsByCandidateId(cnd.getCandidateId());
				if(allAppliedJobs.size()==0) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","No jobs applied by this Candidate"));

//					throw new ResourceNotFoundException("No jobs applied by this Candidate");
				}
			
				List<AppliedJobDTO> appliedJobsDTO = allAppliedJobs.stream().map(appliedJobMapper::mapAppiedJobToApplyjobDTO).toList();
			    
			    return ResponseEntity.ok(appliedJobsDTO);		

		}
		
		public ResponseEntity<?> withdarwJobApplication(@RequestBody Integer ApplicationId, Authentication authentication){
			
			String email = authentication.getName();
			
			AppUser user = appUserRepo.findByEmail(email);

		    Optional<Candidate> candidate = candidateRepo.findByUser(user);
		    
			if(candidate.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
			}
			
			if(applyJobRepo.findById(ApplicationId).isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Application not found"));
				
			}
			applyJobRepo.deleteById(ApplicationId);
			
			
//			in case of multipal delete
//			int rowsDeleted = applyJobRepo.deleteByCandidateIdAndJobId(deleteJob.getCandidateId(),deleteJob.getJobId());
//
//			if (rowsDeleted == 0) {
//			    return ResponseEntity.badRequest().body(Map.of("message","Application not found"));
//			}

			return ResponseEntity.ok(Map.of("message", "Application withdrawn successfully"));
		}
		
		
	
		public ResponseEntity<?> getJobApplication(@RequestBody ApplyJobDTO getJobAppln,Authentication authentication){
			
			String email = authentication.getName();
			
			AppUser user = appUserRepo.findByEmail(email);

		    Optional<Candidate> candidate = candidateRepo.findByUser(user);
		    
			if(candidate.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
			}
			
			Optional<ApplyJob> AppliedJob=applyJobRepo.getJobApplicationByEmpJobCndId(
					getJobAppln.getCandidateId(), getJobAppln.getJobId(),
					getJobAppln.getEmployeerId());
					if(AppliedJob.isEmpty()) {
					
							return ResponseEntity.badRequest().body("Application Not found");	
					}
					ApplyJob aj=AppliedJob.get();
					applyJobRepo.save(aj);
					return ResponseEntity.ok(aj);
		}


}
