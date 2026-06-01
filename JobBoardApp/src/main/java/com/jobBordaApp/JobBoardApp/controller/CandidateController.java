package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.net.ResponseCache;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//@RequestMapping("/jobBoardApp/candidate")
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
	
	  @GetMapping("/candidate/{id}")
	   public ResponseEntity<?> getCandidateByCandidateId(@PathVariable Integer id) {
		
	 	 		return candidateService.getCandidateByCandidateId(id);
	   }
	
	   @PatchMapping("/candidate/{id}")
	   public ResponseEntity<?> updatePartial(@PathVariable Integer id, @RequestBody Candidate updatedCandidate) {
		
		 		return candidateService.updateCandidate(id,updatedCandidate);
	 	}
	
	
	   @DeleteMapping("/candidate/{id}")  //change id to email
	   public ResponseEntity<?> deleteCandidate(@PathVariable Integer id) {
		
		    	return candidateService.deleteCandidate(id);
	 	}
	 
	   @PostMapping("/changePass")
	   public ResponseEntity<?> changePassword( @RequestBody ChangePasswordDTO newPass) {	
				
				return candidateService.changeCandiddatePassword(newPass);
		}
	 
  
//================================Education========================================
	
		@PostMapping("/candidate/{candidateId}/education")
		public ResponseEntity<?> addEducation( @PathVariable Integer candidateId, @RequestBody CandidateEducation education) {
			
				return candidateService.addEducation(candidateId, education);
		}
		
		@PostMapping("/candidate/{candidateId}/{educationId}")
		public ResponseEntity<?> getEducation( @PathVariable Integer candidateId, @PathVariable Integer EduId) {
			
				return candidateService.getEducation(candidateId, EduId);
		}
		

		@PutMapping("/candidate/{candidateId}/education")
		public ResponseEntity<?> updateEducation(@PathVariable Integer candidateId, @RequestBody CandidateEducation updatedEducation) {

				return candidateService.updateEducation(candidateId, updatedEducation);
		}


		@DeleteMapping("/candidate/{candidateId}/education/{educationId}")
		public ResponseEntity<?> deleteEducation( @PathVariable Integer candidateId, @PathVariable Integer educationId) {
			
				return candidateService.deleteEducation(candidateId, educationId);
		}
		
		
//================================Certification========================================
		@PostMapping("/candidate/{candidateId}/certification")
		public ResponseEntity<?> addCertification( @PathVariable Integer candidateId, @RequestBody CandidateCertification certification) {
			
				return candidateService.addCandCertification(candidateId,certification);
		}
		
		
		@PostMapping("/candidate/{candidateId}/{certificationId}")
		public ResponseEntity<?> getCertification( @PathVariable Integer candidateId, @PathVariable Integer CertificationId) {
			
				return candidateService.getCertification(candidateId, CertificationId);
		}
		
		
		@PutMapping("/candidateCerti/{candidateId}")
		public ResponseEntity<?> updateCertification(@PathVariable Integer candidateId, @RequestBody CandidateCertification updatedCedrtification) {
			
				return candidateService.updateCertification(candidateId, updatedCedrtification);
		}


		@DeleteMapping("/candidate/{candidateId}/{certificationId}")
		public ResponseEntity<?> deleteCertificate(@PathVariable Integer candidateId,@PathVariable Integer certificationId) {

				return candidateService.deleteCertification(candidateId, certificationId);
		}
		
				
//================================Experience========================================
		@PostMapping("/candidate/{candidateId}/experience")
		public ResponseEntity<?> addExperience( @PathVariable Integer candidateId, @RequestBody CandidateExperience experience) {
			
			
				return candidateService.addExperience(candidateId, experience);
		}
		
		
		@PostMapping("/candidate/{candidateId}/{experienceId}")
		public ResponseEntity<?> getExperience( @PathVariable Integer candidateId, @PathVariable Integer experienceId) {
			
				return candidateService.getExperience(candidateId, experienceId);
		}
		
		
		@PutMapping("/candidate/{candidateId}/certification")
		public ResponseEntity<?> updateExperience(@PathVariable Integer candidateId, @RequestBody CandidateExperience updatedExperience) {
			
				return candidateService.updateExperience(candidateId, updatedExperience);
		}
		

		@DeleteMapping("/candidate/{candidateId}/experience")
		public ResponseEntity<?> deleteExperience(@PathVariable Integer candidateId,@PathVariable Integer experienceId) {
			
				return candidateService.deleteExperience(candidateId, experienceId);
		}	
										
		
//================================Resume========================================
//		1.View resume should be in resume service			
	
		@PostMapping("/uploadResume/{candidateId}")
	    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer candidateId) {
	    	
	    		return candidateService.uploadResume(file, candidateId);	
	    }
		
		@GetMapping("resume/{resumeId}")
		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer resumeId) throws IOException{
			  
			  return candidateService.getCandidateResume(resumeId);
		}
	  
		@DeleteMapping("/deleteResume/{candidateId}")
	  	public void deleteResume() {
		  
	  	}	  
		
//================================Candidate Job ========================================	
		
		
		@PostMapping("ApplyJob/")
		public ResponseEntity<?> applyJob(@RequestBody ApplyJobDTO jobApplicaation){
			
			 return candidateService.jobApplication(jobApplicaation);
		}
	
		@GetMapping("allAppliedJob/{candidateId}")
		public ResponseEntity<?> allAppliedJobs(@PathVariable Integer candidateId){
			
			 return candidateService.allAppliedJobs(candidateId);
		}
		
		
		@PostMapping("/withdrawApln")
		public ResponseEntity<?> withJobAppln(@RequestBody ApplyJobDTO deleteApplication){
			
			 return candidateService.withdarwJobApplication(deleteApplication);
		}
		
		@PostMapping("/getJob/{applyId}")
		public ResponseEntity<?> getJobAppln(@RequestBody ApplyJobDTO getjob){
			
			 return candidateService.getJobApplication(getjob);
		}
		
		
								
				
	    
	    
	    
//================================Candidate Services End========================================    
	
	    
//		Candidate Object
	//	
//		{
//			"first_name":"Tushar",
//			"last_name":"Patil",
//			"mobile_no":9561500379,
//			"email":"tusharpatil2181@gmail.com",
//			"password":123,
//			"education":"Msc",
//			"skills":"java"
//			
//		}
	    
	    
	    
	  //Candidate Service Implemented	
//		@GetMapping("candidate/{id}")
//		public Candidate getPerticularCandidate(@PathVariable Integer id) {
//			return candidateService.getCandidateByCandidateId(id);
//		}
		
//		@GetMapping("candidate/{email}")
//		public ResponseEntity<Candidate> getCandidatebyEmail(@PathVariable String email) {
//			
//				Optional<Candidate> candidate=	candidateRepo.findByEmail(email);
//				if(candidate.isPresent()) {
////					return ResponseEntity.ok(candidate);
//				}
////					return ResponseEntity.ok(null;)
//		}
		
//		@GetMapping("/candidate/{email}")
//		public ResponseEntity<?> getCandidate(@PathVariable String email) {
	//
//		    Optional<Candidate> existingCandidate = candidateRepo.findByEmail(email);
//		    
//		    if (existingCandidate.isEmpty()) {
//		        return ResponseEntity
//		                .status(HttpStatus.NOT_FOUND)
//		                .body("Candidate not found");
//		    }
//		    
//		    Candidate candidate = existingCandidate.get();
//		    CandidateDTO dto = new CandidateDTO();
//		    
////		    dto.setCandidateId(candidate.getCandidateId());
//		    dto.setFirst_name(candidate.getFirst_name());
//		    dto.setLast_name(candidate.getLast_name());
//		    dto.setMobNo(candidate.getMobNo());
//		    dto.setEmail(candidate.getEmail());
//		    dto.setEducation(candidate.getEducation());
////		    dto.setResume(candidate.getResume());
//		    dto.setSkills(candidate.getSkills());
	//
	//
//		    return ResponseEntity.ok(dto);
//		}
	//	

}
