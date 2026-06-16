package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.net.ResponseCache;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.dto.ApplyJobDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;
import com.jobBordaApp.JobBoardApp.entity.CandidateExperience;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.service.CandidateService;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/jobBoardApp/candidate")
public class CandidateController {
	
	
	@Autowired
	private CandidateRepo candidateRepo;   //Reporisotory variable
	
	@Autowired
	private CandidateService candidateService;   //Reporisotory variable
	
	

	@GetMapping("/candidates")
	public List<Candidate> getAllCandidate() {
		List<Candidate> allCandidates = candidateRepo.findAll();
		if(allCandidates.size()==0) {
			throw new ResourceNotFoundException("Candidate List is empty");
		}
		return allCandidates;
	}

	
//=====================================Implemented service of following functions=====================================
	
//================================Candidate========================================
	
		@GetMapping("/cnd_profile")
		public ResponseEntity<?> getCndProfile(Authentication authentication) {
	
			return candidateService.getCandidateProfile(authentication);
		}
	
	
	   @PatchMapping("/update")
	   public ResponseEntity<?> updatePartial( @RequestBody Candidate updatedCandidate,Authentication authentication) {
		
		 		return candidateService.updateCandidate(updatedCandidate,authentication);
	 	}
	
	
	   @DeleteMapping("/delete")  //change id to email
	   public ResponseEntity<?> deleteCandidate(Authentication authentication) {
		
		    	return candidateService.deleteCandidate(authentication);
	 	}
	 
	   @PostMapping("/changePass")
	   public ResponseEntity<?> changePassword( @RequestBody ChangePasswordDTO newPass,Authentication authentication) {	
				
				return candidateService.changeCandidatePassword(newPass,authentication);
		}
	 
  
//================================Education========================================
	
		@PostMapping("/edu/{candidateId}")
		public ResponseEntity<?> addEducation( @RequestBody CandidateEducation education, Authentication authentication) {
			
				return candidateService.addEducation(education,authentication);
		}
		
		@PostMapping("/edu/{candidateId}/{educationId}")
		public ResponseEntity<?> getEducation(@PathVariable Integer CndId,@PathVariable Integer EduId,Authentication authentication) {
			
				return candidateService.getEducation(CndId,EduId,authentication);
		}
		

		@PutMapping("/edu/{candidateId}/education")
		public ResponseEntity<?> updateEducation(@RequestBody CandidateEducation updatedEducation,Authentication authentication) {

				return candidateService.updateEducation( updatedEducation, authentication);
		}


		@DeleteMapping("/edu/{candidateId}/education/{educationId}")
		public ResponseEntity<?> deleteEducation( @PathVariable Integer candidateId, @PathVariable Integer educationId,Authentication authentication) {
			
				return candidateService.deleteEducation(candidateId, educationId, authentication);
		}
		
		
//================================Certification========================================
		@PostMapping("/newCertification/{candidateId}/certification")
		public ResponseEntity<?> addCertification( @PathVariable Integer candidateId, @RequestBody CandidateCertification certification,Authentication authentication) {
			
				return candidateService.addCandCertification(candidateId,certification,authentication);
		}
		
		
		@PostMapping("/certificate/{candidateId}/{certificationId}")
		public ResponseEntity<?> getCertification( @PathVariable Integer candidateId, @PathVariable Integer CertificationId, Authentication authentication) {
			
				return candidateService.getCertification(candidateId, CertificationId,authentication);
		}
		
		
		@PutMapping("/candidateCerti/{candidateId}")
		public ResponseEntity<?> updateCertification(@PathVariable Integer candidateId, @RequestBody CandidateCertification updatedCedrtification, Authentication authentication) {
			
				return candidateService.updateCertification(candidateId, updatedCedrtification,authentication);
		}


		@DeleteMapping("/candidate/{candidateId}/{certificationId}")
		public ResponseEntity<?> deleteCertificate(@PathVariable Integer candidateId,@PathVariable Integer certificationId,Authentication authentication) {

				return candidateService.deleteCertification(candidateId, certificationId,authentication);
		}
		
				
//================================Experience========================================
		@PostMapping("/candidate/{candidateId}/experience")
		public ResponseEntity<?> addExperience( @PathVariable Integer candidateId, @RequestBody CandidateExperience experience,Authentication authentication) {
			
			
				return candidateService.addExperience(candidateId, experience,authentication);
		}
		
		
		@PostMapping("/candidate/{candidateId}/{experienceId}")
		public ResponseEntity<?> getExperience( @PathVariable Integer candidateId, @PathVariable Integer experienceId,Authentication authentication) {
			
				return candidateService.getExperience(candidateId, experienceId,authentication);
		}
		
		
		@PutMapping("/candidate/{candidateId}/certification")
		public ResponseEntity<?> updateExperience(@PathVariable Integer candidateId, @RequestBody CandidateExperience updatedExperience,Authentication authentication) {
			
				return candidateService.updateExperience(candidateId, updatedExperience,authentication);
		}
		

		@DeleteMapping("/candidate/{candidateId}/experience")
		public ResponseEntity<?> deleteExperience(@PathVariable Integer candidateId,@PathVariable Integer experienceId,Authentication authentication) {
			
				return candidateService.deleteExperience(candidateId, experienceId,authentication);
		}	
										
		
//================================Resume========================================
//		1.View resume should be in resume service			
	
		@PreAuthorize("hasRole('CANDIDATE')")
		@PostMapping("/uploadResume/{candidateId}")
	    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer candidateId,Authentication authentication) {
	    	
	    		return candidateService.uploadResume(file, candidateId, authentication);	
	    }
		
		
//		@PreAuthorize("hasRole('CANDIDATE')")
		@PreAuthorize("hasAnyRole('CANDIDATE','EMPLOYER')")
		@GetMapping("/resume/{resumeId}")
		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer resumeId) throws IOException{
			  
			  return candidateService.getCandidateResume(resumeId);
		}
	  
		@DeleteMapping("/deleteResume/{candidateId}")
	  	public void deleteResume() {
		  
	  	}	  
		
//================================Candidate Job ========================================	
		
		
		@PostMapping("/cnd_ApplyJob/")
		public ResponseEntity<?> applyJob(@RequestBody ApplyJobDTO jobApplicaation,Authentication authentication){
			
			 return candidateService.jobApplication(jobApplicaation,authentication);
		}
	
		@GetMapping("/cnd_allAppliedJob/{candidateId}")
		public ResponseEntity<?> allAppliedJobs(@PathVariable Integer candidateId,Authentication authentication){
			
			 return candidateService.allAppliedJobs(candidateId, authentication);
		}
		
		
		@PostMapping("/cnd_withdrawApln")
		public ResponseEntity<?> withJobAppln(@RequestBody ApplyJobDTO deleteApplication,Authentication authentication){
			
			 return candidateService.withdarwJobApplication(deleteApplication,authentication);
		}
		
		@PostMapping("/getJob/{applyId}")
		public ResponseEntity<?> getJobAppln(@RequestBody ApplyJobDTO getjob,Authentication authentication){
			
			 return candidateService.getJobApplication(getjob,authentication);
		}

	    
//================================Candidate Services End========================================    
	

}
