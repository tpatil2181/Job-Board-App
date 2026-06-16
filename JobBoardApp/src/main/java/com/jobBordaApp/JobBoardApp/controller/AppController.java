package com.jobBordaApp.JobBoardApp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jobBordaApp.JobBoardApp.dto.CandidateRegisterDTO;
import com.jobBordaApp.JobBoardApp.dto.EmployerRegisterDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.service.AppService;
import com.jobBordaApp.JobBoardApp.service.FileService;
import com.jobBordaApp.JobBoardApp.service.JobService;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
//@RequestMapping()
public class AppController {
	
	@Autowired
	private CandidateRepo candidateRepo;   //Reporisotory variable
	
	@Autowired
	private JobService jobService;
	
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
	 
//Login Flow should be first user Login with credential and it gets loginResponce back witch contains token userEmail Id or time etc. and once we get login
// Login DTO at the frontend autometically calls the mehode get profile for there respective controller
//Loign-getProfile

//===========================Candidate Specific Controller ==========================================

		@PostMapping("/cnd_register")
		public ResponseEntity<?> registerCandidate( @RequestBody CandidateRegisterDTO dto) {
			
			return appService.registerCandidate(dto);
		}
		
		@PostMapping("/cnd_login")//Secure login
		public ResponseEntity<?> candLogin (@RequestBody LoginDTO loginRequest) {
		
				return appService.login(loginRequest);
		}
		
		@PostMapping("/emp_register")
		public ResponseEntity<?> registerCompany(@RequestBody EmployerRegisterDTO dto) {
			
				return appService.registerEmployer(dto);
		}
		
		@PostMapping("/emp_login")
		public ResponseEntity<?> companyLogin(@RequestBody LoginDTO loginRequest  ) {

				return appService.login(loginRequest);
		}
		
		
		@PostMapping("/login")//Secure login
		public ResponseEntity<?> Login (@RequestBody LoginDTO loginRequest) {
		
				return appService.login(loginRequest);
		}
			
		
//===========================Main service of job board app Specific Controller ==========================================
		
		
		@GetMapping("/Jobs")
		public ResponseEntity<?> getAllJobs(){

			return jobService.getAllJobs();
		}
		
		
		@GetMapping("/job/{jobId}")
		public ResponseEntity<?> getJob( @PathVariable Integer jobId) {
			
			return jobService.getJobByid(jobId);
		    
		}
		
		@GetMapping("/jobsearch")
		public Page<Job> getAlljobs(@RequestParam(required = false, defaultValue = "1") int pageNo,
									@RequestParam(required = false, defaultValue = "5")  int pageSize,
									@RequestParam(required = false, defaultValue = "jobId") String sortBy,
									@RequestParam(required = false, defaultValue = "ASE")String sortDir,
									@RequestParam(required = false) String jobTitle,
							        @RequestParam(required = false) String jobLocation,
							        @RequestParam(required = false) String employerName,
							        @RequestParam(required = false) Integer minExperience,
							        @RequestParam(required = false) Integer maxExperience,
							        @RequestParam(required = false) WorkMode workMode,
							        @RequestParam(required = false) Integer minSalary,
							        @RequestParam(required = false) Integer maxSalary,
							        @RequestParam(required = false) String employmentType,
							        @RequestParam(required = false) String industryType,
							        @RequestParam(required = false)
							        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
							        LocalDate datePosted){
			Sort sort=null;
			if(sortDir.equalsIgnoreCase("ASE")) {
				sort=Sort.by(sortBy).ascending();
				
			}else {
				sort=Sort.by(sortBy).descending();	
			}
			
			return jobService.findAllJobs(PageRequest.of(pageNo-1, pageSize,sort), jobTitle,jobLocation,employerName,
																				   minExperience,maxExperience,workMode,
																				   minSalary,maxSalary,employmentType,
																				   industryType,datePosted);
																	           
			
			}	
		
		
//		@GetMapping("/jobs")
//		public ResponseEntity<Page<Job>> getAllJobs(@RequestParam(required = false) Integer jobId,@RequestParam(required = false) String employer,
//													@RequestParam(required = false) String jobTitle,@RequestParam(required = false) String status,
//													@RequestParam(required = false) String createDate,Pageable pageable) {
//
//		    return ResponseEntity.ok(jobService.findAllJobs(pageable,jobId,employer,jobTitle,status,createDate));
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
//	@PostMapping("/applyJob")
//	public ResponseEntity<?> applyJob(@RequestBody ApplyJob newApplication) {
//		
//		return appService.JobApplication(newApplication);
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
//	}
	
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
