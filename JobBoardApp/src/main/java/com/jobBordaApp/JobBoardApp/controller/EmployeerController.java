package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.jobBordaApp.JobBoardApp.dto.ChangeJobStatusDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.service.FileService;
import com.jobBordaApp.JobBoardApp.service.JobService;
import com.jobBordaApp.JobBoardApp.service.CandidateService;
import com.jobBordaApp.JobBoardApp.service.EmployerService;

@RestController
@RequestMapping("/Hireflow/employer")
public class EmployeerController {
	
	@Autowired
	private JobRepo jobRepo;   //Reporisotory variable
	
	@Autowired
	private EmployerService EmprService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	@Autowired
	private CandidateService candidateService;   //Reporisotory variable
	
	
	
//================================Employer Profile related service========================================	
	
	@PostMapping("/emp_profile")
	public ResponseEntity<?> getEmployerProfile( Authentication authentication ) {
			
			return EmprService.getCompanyProfile(authentication);
	}
	
	 @PatchMapping("/emp_update")
	 public ResponseEntity<?> updatePartial( @RequestBody Employeer updatedEmployer,Authentication authentication) {
		
		 		return EmprService.updateEmployer(updatedEmployer,authentication);
	 }
	
	
	 @DeleteMapping("/emp_delete")  //change id to email
	 public ResponseEntity<?> deleteEmployer(Authentication authentication) {
		
		    	return EmprService.deleteEmployer(authentication);
	 }
	 
	 @PostMapping("/emp_changePass")
	 public ResponseEntity<?> changePassword( @RequestBody ChangePasswordDTO newPass,Authentication authentication) {	
				
				return EmprService.changeEmployerPassword(newPass,authentication);
	}
	 
	
	
//================================Job releted CURD and service========================================
	
	
//	@PostMapping("/postJob")
//	public ResponseEntity<?> postNewJob( @RequestBody Job newJob) {
//		
//			return EmprService.CreateNewJob(newJob);
//	}
	
	@PostMapping("/postJob")
	public ResponseEntity<?> postNewJob(@RequestBody Job newJob,Authentication authentication) {

	    return EmprService.createNewJob(newJob, authentication);
	}
	
	@GetMapping("/job/{id}")
	public ResponseEntity<?> getJob( @PathVariable Integer jobId ) {
			
			return jobService.getJobByid(jobId);
	}
	
	@PatchMapping("/job/{id}")
	public ResponseEntity<?> updateJob( @PathVariable Integer id,@RequestBody Job updatedJob) {
		
			return EmprService.updateJob(id,updatedJob);	
	}
	
	@DeleteMapping("/job/{jobId}/{EmployeerId}")
	public ResponseEntity<?> deleteJob( @PathVariable Integer jobId,@PathVariable Integer EmployeerId) {
		
			return EmprService.DeleteExistingJob(jobId,EmployeerId);
	}
	

	@GetMapping("/AllPostedJobs/{employeerId}")
	public ResponseEntity<?> getAllJobsPostedByCompany(@PathVariable Integer employeerId){
		
			return EmprService.getAllJobsPostedByCompany(employeerId);
	}
	
	@GetMapping("/allAppln/{jobId}")
	public ResponseEntity<?> getAllApplicant(@PathVariable Integer jobId){
		
			return EmprService.getAllApplicantByJob(jobId);
	}
	
	@PostMapping("/changeStatus")
	public ResponseEntity<?> chngJbSta(@RequestBody ChangeJobStatusDTO updatedStatus) {

	    return EmprService.changeJobStatus(updatedStatus );
	}
	
	

	
	
	
	
	
	
	
	
//=================================================================================
	
//	{
//		  "employeerName":"Google",
//		  "website":"www.google.com",
//		  "email":"Gogole@gmail.com",
//		  "password":3333,
//		  "contact":9090909090,
//		  "joblist":"L"
//		}
	
//	{
//		  "employer": { "employeerId": 1 },
//		  "job_title":"java developer",
//		  "job_discription":"3333",
//		  "status":"L",
//		  "create_date":"12/04/2026"
//		}
//	
	
	

	
//	@GetMapping("/test")
//	public String testEmoployeer() {
//		return "Employeer run successfully";
//	}
	
	
//==============Comapny  releted  CURD operations by company=================
	
	
//	@PostMapping("/register")
//	public String registerCompany(@RequestBody Employeer company) {
//		
//		Optional<Employeer> existingCompany= employeerRepo.findByEmail(company.getEmail());
//		if(existingCompany.isPresent()) {
//			throw new RuntimeException("Company already Register");
//		}
//		employeerRepo.save(company);
//		return "Company Registered Successfully";	
//		
//	}
//	
//	@PostMapping("/login")
//	public String companyLogin(@RequestBody LoginDTO request  ) {
//		
//		Employeer company = employeerRepo.findByEmail(request.getEmail())
//	                .orElseThrow(() -> new RuntimeException("Company not found"));
//
//	        if (!company.getPassword().equals(request.getPassword())) {
//	            throw new ResourceNotFoundException( "Invalid password");
//	        }
//			return "Company login successfully";
//	}
	
	
//	@GetMapping("/company/{employeerId}")
//	public Employeer getCompanyProfile(@PathVariable int companyId) {
//		Employeer company= employeerRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company not found with this id"));
//		return company;
//	}
	
	@PutMapping("/company/{employeerId}")
	public String updateCompanyProfile(@PathVariable int companyId, @RequestBody Employeer updatedComapny) {
		
		
		Employeer existingCompany= employeerRepo.findById(companyId).orElseThrow();
		
		
		
		existingCompany.setEmployeerName(updatedComapny.getEmployeerName());
		existingCompany.setWebsite(updatedComapny.getWebsite());
		existingCompany.setContact(updatedComapny.getContact());
//		existingCompany.setEmail(updatedComapny.getEmail());
		existingCompany.setJoblist(updatedComapny.getJoblist());
		//Comment job list beacouse it should not be part of Employeer entity
		    
		employeerRepo.save(existingCompany);
		
		return "Company profile updated";
	}
	
	@DeleteMapping("/company/{id}")
	public String deleteCompany(@PathVariable int companyId) {
		Employeer company= employeerRepo.findById(companyId).orElseThrow();
		employeerRepo.delete(company);
		return "Companny deleted";
	}
	
	
//==============Other Services=================	
	
	@GetMapping("/allCompanies ")
	public List<Employeer> getAllCompanies() {
		List<Employeer> companyies=employeerRepo.findAll();
		return companyies;
		
	}
	
	
	
//	@PatchMapping("updateStatus/{ApplyJobId}")
//	public String changeStatusOfPerticularJobApplication()
	
	
	
//==============Job releted operations by company=================
	
	
	
	
//	@PostMapping("/job/{jobId}")
//	public Job getJob( @PathVariable Integer jobId) {
//		Job job = jobRepo.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job not found"));
//		return job;
////		jobRepo.delete(job);
////	    return "Job Deleted successfully";	
//	}
	
	
	
	
	
	@PostMapping("/updateJob")
	public String updateJobInfo( @RequestBody Job updatedJob) {
		
		Job existingJob = jobRepo.findById(updatedJob.getJobId()).orElseThrow();
		// Update fields
		existingJob.setJobTitle(updatedJob.getJobTitle());
		existingJob.setJobDescription(updatedJob.getJobDescription());
		existingJob.setStatus(updatedJob.getStatus());
	    jobRepo.save(updatedJob);
	    return "Job updated successfully";	
		
	}
	
//	@PostMapping("/updateJobStatus")
//	public String updateJobStatus( @RequestBody Job newJob) {
//		
//		
//		List<Employeer> companyies=employeerRepo.findAll();
//		jobRepo.save(newJob);
//		
//		return "Job Created Successfully";
//		
//	}
	
	
	
	
	
	
//============== Candidate-Job Application releted operations by company=================
	
	@PatchMapping("/applyJobStatus/{applyid}/{status}")
	public String changeApplyJobStatus(@PathVariable Integer applyId,@PathVariable String Status) {
		ApplyJob application=applyJobRepo.findById(applyId).orElseThrow(()-> new ResourceNotFoundException("Job application not found with this ID"));
		application.setStatus(Status);
		applyJobRepo.save(application);
		return "Job Status Updated Successfully";
	}
	
	
	
//============== Candidate operations by company=================
	
//	@GetMapping("/getCandidate/{candidateId}")
//	public ResponseEntity<?> getPerticularCandidate(@PathVariable Integer id) {
//		return candidateService.getCandidateByCandidateId(id);
//	}
//	
	@GetMapping("resume/{candidateId}")
	public ResponseEntity<org.springframework.core.io.Resource> getResume(@PathVariable Integer candidateId) throws IOException{
			return fileService.getResume(candidateId);
	}
	
	//create a function which will return of Candidates by search for particular skill

	
	
	

}
