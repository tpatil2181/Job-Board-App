package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


@RestController
//@RequestMapping("/jobBoardApp/candidate")
public class CandidateController {
	
	
	
//	Candidate Object
//	
//	{
//		"first_name":"Tushar",
//		"last_name":"Patil",
//		"mobile_no":9561500379,
//		"email":"tusharpatil2181@gmail.com",
//		"password":123,
//		"education":"Msc",
//		"skills":"java"
//		
//	}
	
	
	
	@GetMapping("/testCandidate")
	public String testProj() {
		
		return "Candidate Run successfully";
	}
	
	
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

	
	@PostMapping("/register")
	public String registerCandidate(@RequestBody Candidate newCandidate) {
		
		Optional<Candidate> existingCandidate=candidateRepo.findByEmail(newCandidate.getEmail());
		if(existingCandidate.isPresent()) {
//			throw new RuntimeException("Candidate already exist");
			return "Candidate already exist";
		}
		candidateRepo.save(newCandidate);
		return "Candidate Register Successfully";	
	}
	
	
	@PostMapping("/login")
	public String candidateLogin(@RequestBody LoginDTO request) {

		Candidate candidate = candidateRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        if (!candidate.getPassword().equals(request.getPassword())) {
            throw new ResourceNotFoundException( "Invalid password");
        }
		return "Candidate login successfully";
	}
	
	
	@GetMapping("/candidates")
	public List<Candidate> getAllCandidate() {
		List<Candidate> allCandidates = candidateRepo.findAll();
		if(allCandidates.size()==0) {
			throw new ResourceNotFoundException("Candidate List is empty");
		}
		return allCandidates;
	}
	
	
//Candidate Service Implemented	
	@GetMapping("candidate/{id}")
	public Candidate getPerticularCandidate(@PathVariable Integer id) {
		return candidateService.getCandidateByCandidateId(id);
	}
	
	
	
	@PutMapping("/candidate")
	public String updateCandidateInfo(@RequestBody Candidate updatedCandidate) {
		 
		Candidate existingCandidate = candidateRepo.findById(updatedCandidate.getCandidateId()).orElseThrow();
		// Update fields
	    existingCandidate.setFirst_name(updatedCandidate.getFirst_name());
	    existingCandidate.setLast_name(updatedCandidate.getLast_name());
	    existingCandidate.setMobNo(updatedCandidate.getMobNo());
	    existingCandidate.setEmail(updatedCandidate.getEmail());
	    existingCandidate.setPassword(updatedCandidate.getPassword());
	    existingCandidate.setEducation(updatedCandidate.getEducation());
	    existingCandidate.setSkills(updatedCandidate.getSkills());
	    
	    candidateRepo.save(existingCandidate);
	    return "Candidate updated successfully";		
	}
	
	@DeleteMapping("/candidate/{id}")
	public String deleteCandidate(@PathVariable Integer id) {
		Candidate candidate = candidateRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate not found"));  
		candidateRepo.delete(candidate);
	    return "Candidate Deleted successfully";		
	}
	
	
	@PatchMapping("/changePass/{id}")
	public String changePassword(@PathVariable Integer id, @RequestBody ChangePasswordDTO newPass) {
		
		Candidate candidate=candidateRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate not found"));
		
		//Forntend vaidation 
//		1 old pass is not same to new pass
//		2 new pass and conform pass should be match
		if(!newPass.getCurrentPass().equals(candidate.getPassword())){
			throw new ResourceNotFoundException("Your current password is not matching");
		}
		candidate.setPassword(newPass.getNewPass());
		candidateRepo.save(candidate);
		
		return "Password Changed";
		
	}
	
	@PatchMapping("/candidate/{id}")
	public Candidate updatePartial(@PathVariable Integer id, @RequestBody Candidate updatedCandidate) {

		Candidate existingCandidate = candidateRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    if (updatedCandidate.getFirst_name() != null)
	        existingCandidate.setFirst_name(updatedCandidate.getFirst_name());

	    if (updatedCandidate.getLast_name() != null)
	        existingCandidate.setLast_name(updatedCandidate.getLast_name());

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
	    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer candidateId) {
	    	
	        try {
	            int resumeId = fileService.uploadOrUpdateResume(candidateId,file);
	            Candidate cnd=candidateRepo.findById(candidateId).orElseThrow(()-> new ResourceNotFoundException("Candidate Not found with this ID"));
	            CandidateResume CR=candidateResumeRepo.findById(resumeId).orElseThrow(()-> new ResourceNotFoundException("Resume Not found with this ID"));;
	            cnd.setResume(CR);
	            candidateRepo.save(cnd);
	            return "File uploaded successfully at: " + CR.getPath();

	        } catch (Exception e) {
	            return "File upload failed: " + e.getMessage();
	        }
	    }
	    
	    
	    
	    @GetMapping("resume/{CandidateId}")
		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer CandidateId) throws IOException{
				return fileService.getResume(CandidateId);
		}
	    
	    
	    
		@GetMapping("candidate/{id}/applications")
		public List<ApplyJob> getAllApplicationOfPerticularCandidate(@PathVariable Integer candidateId) {
			
			List<ApplyJob> allApplications= applyJobRepo.findAllApplicationsByCandidateId(candidateId);
			if(allApplications.isEmpty()) {
				throw new ResourceNotFoundException("Not appied for any job");
			}
			return allApplications;
		}
		
	
	    
//================================Candidate Services End========================================
	    

}
