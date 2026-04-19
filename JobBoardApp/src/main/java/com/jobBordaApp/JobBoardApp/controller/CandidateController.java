package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.net.ResponseCache;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateResume;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateResumeReop;
import com.jobBordaApp.JobBoardApp.service.FileService;
import com.jobBordaApp.JobBoardApp.service.CandidateService;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
//@RequestMapping("/jobBoardApp/candidate")
public class CandidateController {
	
	
	@Autowired
	private CandidateRepo candidateRepo;   //Reporisotory variable
	
	@Autowired
	private CandidateService candidateService;   //Reporisotory variable
	
	@Autowired
	private CandidateResumeReop candidateResumeRepo;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	
	
//================Candidate CURD operation========================


	
	
	@GetMapping("/candidates")
	public List<Candidate> getAllCandidate() {
		List<Candidate> allCandidates = candidateRepo.findAll();
		if(allCandidates.size()==0) {
			throw new ResourceNotFoundException("Candidate List is empty");
		}
		return allCandidates;
	}
	
	

	
	
	@PutMapping("/candidate")
	public String updateCandidateInfo(@RequestBody Candidate updatedCandidate) {
		 
		Candidate existingCandidate = candidateRepo.findById(updatedCandidate.getCandidateId()).orElseThrow();
		// Update fields
	    existingCandidate.setFirstName(updatedCandidate.getFirstName());
	    existingCandidate.setLastName(updatedCandidate.getLastName());
	    existingCandidate.setMobNo(updatedCandidate.getMobNo());
	    existingCandidate.setEmail(updatedCandidate.getEmail());
	    existingCandidate.setPassword(updatedCandidate.getPassword());
	    existingCandidate.setEducation(updatedCandidate.getEducation());
	    existingCandidate.setSkills(updatedCandidate.getSkills());
	    
	    candidateRepo.save(existingCandidate);
	    return "Candidate updated successfully";		
	}
	
	@DeleteMapping("/candidate/{id}")  //change id to email
	public String deleteCandidate(@PathVariable Integer id) {
		Candidate candidate = candidateRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate not found"));  
		candidateRepo.delete(candidate);
	    return "Candidate Deleted successfully";		
	}
	
	
	@PostMapping("/changePass/{id}")
	public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody ChangePasswordDTO newPass) {
		
		Candidate candidate=candidateRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate not found"));
		
		//Forntend vaidation 
//		1 old pass is not same to new pass
//		2 new pass and conform pass should be match
		if(!newPass.getCurrentPass().equals(candidate.getPassword())){
			System.out.println("New pass current pass "+newPass.getCurrentPass());
			System.out.println("Existing pass current pass "+candidate.getPassword());
			throw new ResourceNotFoundException("Your current password is not matching");
		}
		candidate.setPassword(newPass.getNewPass());
		candidateRepo.save(candidate);
		
//		return "Password Changed";
		return ResponseEntity.ok(Map.of(
		        "message", "Password changed successfully"
		    ));
		
	}
	
	@PatchMapping("/candidate/{id}")
	public Candidate updatePartial(@PathVariable Integer id, @RequestBody Candidate updatedCandidate) {

		Candidate existingCandidate = candidateRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    if (updatedCandidate.getFirstName() != null)
	        existingCandidate.setFirstName(updatedCandidate.getFirstName());

	    if (updatedCandidate.getLastName() != null)
	        existingCandidate.setLastName(updatedCandidate.getLastName());

	    if (updatedCandidate.getMobNo() != null)
	        existingCandidate.setMobNo(updatedCandidate.getMobNo());

	    if (updatedCandidate.getEmail() != null)
	        existingCandidate.setEmail(updatedCandidate.getEmail());

	    if (updatedCandidate.getPassword() != null)
	        existingCandidate.setPassword(updatedCandidate.getPassword());

	    if (updatedCandidate.getEducation() != null)
	        existingCandidate.setEducation(updatedCandidate.getEducation());

	    if (updatedCandidate.getSkills() != null)
	        existingCandidate.setSkills(updatedCandidate.getSkills());

	    return candidateRepo.save(existingCandidate);
	}
	


	    @PostMapping("/uploadResume/{candidateId}")
	    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer candidateId) {
	    	
	        try {
	            int resumeId = fileService.uploadOrUpdateResume(candidateId,file);
	            Candidate cnd=candidateRepo.findById(candidateId).orElseThrow(()-> new ResourceNotFoundException("Candidate Not found with this ID"));
	            CandidateResume CR=candidateResumeRepo.findById(resumeId).orElseThrow(()-> new ResourceNotFoundException("Resume Not found with this ID"));;
	            cnd.setResume(CR);
	            candidateRepo.save(cnd);
//	            return "File uploaded successfully at: " + CR.getPath();
	            return ResponseEntity.ok(Map.of(
	    		        "message", "Resume uploaded successfully at:" + CR.getPath()
	    		    ));

	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(Map.of(
					    "message",  "File upload failed: " + e.getMessage()
					));
	        }
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
		
	    
		
//		@PostMapping("/register")
//		public ResponseEntity<?> registerCandidate(@RequestBody Candidate newCandidate) {
//			
//			Optional<Candidate> existingCandidate=candidateRepo.findByEmail(newCandidate.getEmail());
//			if(existingCandidate.isPresent()) {
////				throw new RuntimeException("Candidate already exist");
//				 return ResponseEntity.badRequest().body(Map.of(
//						    "message", "Candidate already exist"
//						));
//			}
//			candidateRepo.save(newCandidate);
////			return "Candidate Register Successfully";	
//			 return ResponseEntity.ok(Map.of(
//				        "message", "Candidate Registered Successfully"
//				    ));
//			 
//		}
		
		
		
//		@PostMapping("/login")
//		public ResponseEntity<?> candidateLogin(@RequestBody LoginDTO request) {
	//
//			Candidate candidate = candidateRepo.findByEmail(request.getEmail())
//	                .orElseThrow(() -> new RuntimeException("Candidate not found"));
	//
//	        if (!candidate.getPassword().equals(request.getPassword())) {
//	            return ResponseEntity.badRequest().body(Map.of(
//					    "message", "Invalid password"
//					));
//	        }
////			return "Candidate login successfully";
	// CandidateDTO dto = new CandidateDTO();
//		    
////		    dto.setCandidateId(candidate.getCandidateId());
//		    dto.setFirst_name(candidate.getFirst_name());
//		    dto.setLast_name(candidate.getLast_name());
//		    dto.setMobNo(candidate.getMobNo());
//		    dto.setEmail(candidate.getEmail());
//		    dto.setEducation(candidate.getEducation());
//		    dto.setResumeId(candidate.getResume().getResumeId());
////		    dto.setResume(candidate.getResume());
//		    dto.setSkills(candidate.getSkills());
	//
	//
//		    return ResponseEntity.ok(dto);
////	        return ResponseEntity.ok(Map.of(
////			        "message", "Candidate login successfully"
////			    ));
//		}
	    
	    
	    
	    
	    
	    
//	    @GetMapping("resume/{resumeId}")
//		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer resumeId) throws IOException{
//				return fileService.getResume(resumeId);
//		}
//	    
//		@GetMapping("candidate/{id}/applications")
//		public List<ApplyJob> getAllApplicationOfPerticularCandidate(@PathVariable Integer candidateId) {
//			
//			List<ApplyJob> allApplications= applyJobRepo.findAllApplicationsByCandidateId(candidateId);
//			if(allApplications.isEmpty()) {
//				throw new ResourceNotFoundException("Not appied for any job");
//			}
//			return allApplications;
//		}
		
	    
//================================Candidate Services End========================================
	    

}
