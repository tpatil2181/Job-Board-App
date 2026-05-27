package com.jobBordaApp.JobBoardApp.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.RecordAvailableException;
import com.jobBordaApp.JobBoardApp.mapper.CandidateMapper;
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
	
	 
//	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); 
	 
	@Autowired
	private CandidateMapper candidateMapper;
	
	
//---------------------------------------------------------------------------------------------------------

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
