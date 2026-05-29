package com.jobBordaApp.JobBoardApp.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.JobDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.RecordAvailableException;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.mapper.CandidateMapper;
import com.jobBordaApp.JobBoardApp.mapper.JobMapper;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;

@Service
public class AppService {
	
	@Autowired
	private CandidateRepo candidateRepo;   //Reporisotory variable
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private ApplyJobRepo applicationRepo;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private JobMapper jobMapper;
	
	 
//	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); 
	 
	
	
	
//===========================Candidate Specific service ==========================================
	 
	
		public ResponseEntity<?> registerCandidate(@RequestBody Candidate newCandidate) {
			
			Candidate existingCandidate=candidateRepo.findByEmail(newCandidate.getEmail());
			if(existingCandidate!=null) {
				 return ResponseEntity.badRequest().body(Map.of("message", "Candidate already exist"));
				}
	//					newCandidate.setPassword(encoder.encode(newCandidate.getPassword()));
				candidateRepo.save(newCandidate);	
			 return ResponseEntity.ok(Map.of("message", "Candidate Registered Successfully")); 
		}
		
		
		
		
//		Implemented Spring security and password encoder decoder and the following request by spring security and authent
		
//		@GetMapping("/profile")(Login)
//		public ResponseEntity<?> getProfile(Authentication authentication) {
//
//		    String email = authentication.getName();
//		    Candidate candidate = candidateRepo.findByEmail(email);
//		    CandidateDTO candidateDTO =candidateMapper.mapCandidateToCandidateDTO(candidate);
//
//		    return ResponseEntity.ok(candidateDTO);
//		}
		
		
	
		public ResponseEntity<?> candidateLogin(@RequestBody LoginDTO request) {
				Candidate candidate = candidateRepo.findByEmail(request.getEmail());
				if(candidate==null) 
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
	
				if (!candidate.getPassword().equals(request.getPassword()))
					return ResponseEntity.badRequest().body(Map.of( "message", "Invalid password"));
	
				CandidateDTO candidateDto=candidateMapper.mapCandidateToCandidateDTO(candidate);	
					return ResponseEntity.ok(candidateDto);
			//	    return ResponseEntity.ok(Map.of("message", "Candidate login successfully" ));
			}
	
	
	
	 

		
		

		
	
//-------------------------------------------------------------------------
	
	@GetMapping("/candidate/{email}")
	public ResponseEntity<?> getCandidate(@PathVariable String email) {

		    Candidate existingCandidate = candidateRepo.findByEmail(email);
		    
		    if (existingCandidate==null) {
		        return ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .body("Candidate not found");
		    }
		    
//		    Candidate candidate = existingCandidate();
		    CandidateDTO dto = new CandidateDTO();
		    
	//	    dto.setCandidateId(candidate.getCandidateId());
//		    dto.setFirst_name(candidate.getFirstName());
//		    dto.setLast_name(candidate.getLastName());
//		    dto.setMobNo(candidate.getMobNo());
//		    dto.setEmail(candidate.getEmail());
//		    dto.setEducation(candidate.getEducation());
	//	    dto.setResume(candidate.getResume());
//		    dto.setSkills(candidate.getSkills());
	
	
		    return ResponseEntity.ok(dto);
	}
	
	 
//===========================Candidate Specific service End ==========================================
	
	

	
//===========================Company(Employer) Specific service ==========================================

	
	public ResponseEntity<?> employerRegister(@RequestBody Employeer company) {
	
			Employeer existingCompany= employeerRepo.findByEmail(company.getEmail());
			if(existingCompany==null) {
				throw new RuntimeException("Company already Register");
			}
			employeerRepo.save(company);
			return ResponseEntity.ok(Map.of("message", "Company Registered Successfully" ));
	}
	
	
	
	
//	Implemented Spring security and password encoder decoder and the following request by spring security and authent
	
//	ALSO IMPLEMENTE SPRING SECURITY FOR COMPANY 
	
//	@GetMapping("/profile")(Login)
//	public ResponseEntity<?> getProfile(Authentication authentication) {
//
//	    String email = authentication.getName();
//	    Candidate candidate = candidateRepo.findByEmail(email);
//	    CandidateDTO candidateDTO =candidateMapper.mapCandidateToCandidateDTO(candidate);
//
//	    return ResponseEntity.ok(candidateDTO);
//	}
	
	


	public ResponseEntity<?> employerLogin(@RequestBody LoginDTO request  ) {
		
		return ResponseEntity.badRequest().body(Map.of( "message", "Company login OK"));
		
//		Employeer company = employeerRepo.findByEmail(request.getEmail());
//			if(company==null) 
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
//
//	        if (!company.getPassword().equals(request.getPassword())) {
//	        	return ResponseEntity.badRequest().body(Map.of( "message", "Invalid password"));
//	        }
//	        
//	        CandidateDTO candidateDto=candidateMapper.mapCandidateToCandidateDTO(company);
//	        
//			return ResponseEntity.ok(candidateDto);
//			return "Company login successfully";
	}
	
//===========================Company(Employer) Specific service ==========================================
	
	

	
	
	
//===========================Main service of job board app Specific service ==========================================
	
	public ResponseEntity<?> getPerticularJob(@PathVariable Integer jobId){
		
			Job job = jobRepo.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

	    	JobDTO jobDTO =jobMapper.mapJobToJobDTO(job);

	    return ResponseEntity.ok(jobDTO);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<?> JobApplication(@RequestBody  ApplyJob newApplication) { 
		
		
		Integer candidateId = newApplication.getCandidate().getCandidateId();
		Integer jobId = newApplication.getJob().getJobId();
		
		Optional<Candidate> candidate = candidateRepo.findById(candidateId);
		if(candidate.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
		
		Optional<Job> job =jobRepo.findById(jobId);
		if(job.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job not found"));
		
		Optional<ApplyJob> isApplied=applicationRepo.findByUserIdAndJobId(candidateId,jobId);
	    
	    if(isApplied.isPresent()) 
	    	return ResponseEntity.badRequest().body(Map.of( "message", "You have already applied for this job"));

		
//		 newApplication.setCandidate(candidate); 
//		 newApplication.setJob(job);   
		 
		 applicationRepo.save(newApplication);
		 
		 return ResponseEntity.ok(Map.of("message", "Apply for job successfully" ));

		
	}

}
