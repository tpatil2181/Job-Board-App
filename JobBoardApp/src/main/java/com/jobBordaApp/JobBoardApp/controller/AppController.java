package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateRegisterDTO;
import com.jobBordaApp.JobBoardApp.dto.EmployerRegisterDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.RecordAvailableException;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.mapper.CandidateMapper;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.service.AppService;
import com.jobBordaApp.JobBoardApp.service.FileService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
//@RequestMapping()
public class AppController {
	
	@Autowired
	private CandidateRepo candidateRepo;   //Reporisotory variable
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	@Autowired
	private AppService appService;
	
	
	
	
	
@GetMapping("/test")
public String test() {
	return "Job board app run successfully";
}


//ONLY JOB SEARCH FEATURE IS REMEAINING
	 


//===========================Candidate Specific Controller ==========================================

		@PostMapping("/cnd_register")
		public ResponseEntity<?> registerCandidate( @RequestBody CandidateRegisterDTO dto) {
			
			return appService.registerCandidate(dto);
		}
		
		@PostMapping("cnd_login")//Secure login
		public String candLogin (@RequestBody LoginDTO loginDTO) {
		
				return appService.verify(loginDTO);
		}
		
		@PostMapping("emp_register")
		public ResponseEntity<?> registerCompany(@RequestBody EmployerRegisterDTO dto) {
			
				return appService.employerRegister(dto);
		}
		
		@PostMapping("/emp_login")
		public ResponseEntity<?> companyLogin(@RequestBody LoginDTO request  ) {

				return appService.employerLogin(request);
		}
		
//		@PostMapping("/candidate/login")
//		public ResponseEntity<?> candLogin(@RequestBody LoginDTO request) {
//		
//				return appService.candidateLogin(request);
//		}
		
		//Implementated Spring Security
//		@PostMapping("/candidate/login")//Secure login
//		public ResponseEntity<?> candLogin(Authentication authentication) {
//		
//				return appService.getProfile(authentication);
//		}
		
		
//		completed till 2.37 generating token successfully and remaining is validating the token
//		
		
//===========================Main service of job board app Specific Controller ==========================================
		
		
		@GetMapping("/Jobs")
		public List<Job> getAllJobs(){

			List<Job> allJobs= jobRepo.findAll();
			return allJobs;	
		}
		
//		
//		@GetMapping("/job/{jobId}")
//		public ResponseEntity<?> getJob( @PathVariable Integer jobId) {
//			
//			return appService.getPerticularJob(jobId);
//		    
//		}

	 	
		

		
		
//-------------------------------------------------------------------------
//	this should  be in company service only company can view candidate profile on candidate  email
//	@GetMapping("/candidate/{email}")
//	public ResponseEntity<?> getCandidate(@PathVariable String email) {
//
//		    Candidate existingCandidate = candidateRepo.findByEmail(email);
//		    
//		    if (existingCandidate==null) {
//		        return ResponseEntity
//		                .status(HttpStatus.NOT_FOUND)
//		                .body("Candidate not found");
//		    }
//		    
////		    Candidate candidate = existingCandidate();
//		    CandidateDTO dto = new CandidateDTO();
//		    
//	//	    dto.setCandidateId(candidate.getCandidateId());
////		    dto.setFirst_name(candidate.getFirstName());
////		    dto.setLast_name(candidate.getLastName());
////		    dto.setMobNo(candidate.getMobNo());
////		    dto.setEmail(candidate.getEmail());
////		    dto.setEducation(candidate.getEducation());
//	//	    dto.setResume(candidate.getResume());
////		    dto.setSkills(candidate.getSkills());
//	
//	
//		    return ResponseEntity.ok(dto);
//	}
	
	 
//===========================Candidate Specific service End ==========================================
	
	
//	
//	 @GetMapping("resume/{resumeId}")
//		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer resumeId) throws IOException{
//				return fileService.getResume(resumeId);
//		}
	    
		@GetMapping("candidate/{id}/applications")
		public List<ApplyJob> getAllApplicationOfPerticularCandidate(@PathVariable Integer candidateId) {
			
			List<ApplyJob> allApplications= applyJobRepo.findAllApplicationsByCandidateId(candidateId);
			if(allApplications.isEmpty()) {
				throw new ResourceNotFoundException("Not appied for any job");
			}
			return allApplications;
		}
		
		
		
		
		
//===========================Company Specific service =========================================		
		

		
		
		@GetMapping("/company/{employeerId}")
		public Employeer getCompanyProfile(@PathVariable int companyId) {
			Employeer company= employeerRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company not found with this id"));
			return company;
		}
		
		
		
//===========================Company Specific service End =========================================
		
		
//===========================Job Specific service  =========================================
		

		
		
		
		
		
		
		
//===========================Job Specific service End =========================================
	 
	 
//============================================================================= 
	
//		{
//		  "employeerName":"Vikram",
//		  "website":"www.google.com",
//		  "email":"vikram.singh@gmail.com",
//		  "password":3333,
//		  "contact":9090909090,
//		  "joblist":"L"
//		}
	
	
//	{
//		  "user": { "userId": 1 },
//		  "job": { "jobId": 1 },
//		  "employeerId": { "employeerId": 1 },
//		  "status": "Applied"
//		}
//	
	
	
	
//	Apply for the job
//	@PostMapping("/applyJob")
//	public String userApplyForJob(@RequestBody Job ) {
//		return "applied";
//	}
//	
	
//====================================Spring security part====================================	
	
	//Getting session id
//	@GetMapping("/")
//	public String testsecurity(HttpServletRequest request)
//	{
//		
//		return "hii    "+	request.getSession().getId();
//	
//	}
//
//	@GetMapping("/getCsrf")
//	public CsrfToken getCsrfToken(HttpServletRequest request)
//	{
//		
//		return (CsrfToken)request.getAttribute("_csrf");
//		//Gertting problem to create obkject form postman
//	}

	
//====================================Spring security part End ====================================	
	
	
	
	
	
//Controlers which should be app controller
//	1.Create job using comany id
//	2.Apply job user comopany and job
//	3.
//	4.
//	5.
//	6.
//	7.
	
	
	

//		
		
	

	
	
//	@GetMapping("/{employeerId}/allAppliedUser/{JobId}")
//	public List<User> getAllAppliedUserByJobId(@PathVariable Integer employeerId, @PathVariable Integer JobId ){
//		
////		Job applicationRepo.
//		
//		return 
//		
//		
//		
//	}
	
	
	
//	@PostMapping("/applyJob")
//	public String applyForJob(@RequestBody ApplyJob newApplication) {
//		applicationRepo.save(newApplication);
//		return "Job applied Sussfully";
//	}
//	
	@PostMapping("/applyJob")
	public ResponseEntity<?> applyJob(@RequestBody ApplyJob newApplication) {
		
		return appService.JobApplication(newApplication);
//
//	    Integer candidateId = newApplication.getCandidate().getCandidateId();
//	    Integer jobId = newApplication.getJob().getJobId();
//
//	    Candidate candidate = candidateRepo.findById(candidateId)
//	            .orElseThrow(() -> new RuntimeException("Candidate not found"));
//
//	    Job job = jobRepo.findById(jobId)
//	            .orElseThrow(() -> new RuntimeException("Job not found"));
//	    
//	    Optional<ApplyJob> isApplied=applicationRepo.findByUserIdAndJobId(candidateId,jobId);
//	    
//	    if(isApplied.isPresent()) {
//	    	throw new RecordAvailableException("You have already applied for this job");
//	    }
//
//	    newApplication.setCandidate(candidate);  // ✅ managed entity
//	    newApplication.setJob(job);    // ✅ managed entity
//
//	    applicationRepo.save(newApplication);
//
//	    return "Applied Successfully";
	}
	
//Ambiguty error comming for this methode for same name get resume	
//	@GetMapping("resume/{userId}")
//	public ResponseEntity<org.springframework.core.io.Resource> getapplicantResume(@PathVariable Integer userId) throws IOException{
//			return fileService.getResume(userId);
//	}
	
	
	
//	@GetMapping("/allJobs/{employeerId}")
//	public List<Job> getPerticularCompanyJobs(@PathVariable int companyId){
//		List<Job> allJobs= jobRepo.findJobsByCompanyId(companyId);
//		if(allJobs.size()==0) {
//			System.out.println("No jobs found with comapny Id: "+companyId);
//			return allJobs;
//		}
//		else
//			return allJobs;
//		return allJobs;
//		
//	}

}
