package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

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

import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;
import com.jobBordaApp.JobBoardApp.service.FileService;
import com.jobBordaApp.JobBoardApp.service.UserService;

@RestController
@RequestMapping("/jobBoardApp/employeer")
public class EmployeerController {
	
	@Autowired
	private JobRepo jobRepo;   //Reporisotory variable

	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	
	@Autowired
	private UserService userService;   //Reporisotory variable
	
	
	
	
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
	
	

	
	@GetMapping("/test")
	public String testEmoployeer() {
		return "Employeer run successfully";
	}
	
	
//==============Comapny  releted  CURD operations by company=================
	
	
	@PostMapping("/register")
	public String registerCompany(@RequestBody Employeer company) {
		
		Optional<Employeer> existingCompany= employeerRepo.findByEmail(company.getEmail());
		if(existingCompany.isPresent()) {
			throw new RuntimeException("Company already Register");
		}
		employeerRepo.save(company);
		return "Company Registered Successfully";	
		
	}
	
	@PostMapping("/login")
	public String companyLogin(@RequestBody LoginDTO request  ) {
		
		Employeer company = employeerRepo.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("Company not found"));

	        if (!company.getPassword().equals(request.getPassword())) {
	            throw new ResourceNotFoundException( "Invalid password");
	        }
			return "Company login successfully";
	}
	
	
	@GetMapping("/company/{employeerId}")
	public Employeer getCompanyProfile(@PathVariable int companyId) {
		Employeer company= employeerRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company not found with this id"));
		return company;
	}
	
	@PutMapping("/company/{employeerId}")
	public String updateCompanyProfile(@PathVariable int companyId, @RequestBody Employeer updatedComapny) {
		
		
		Employeer existingCompany= employeerRepo.findById(companyId).orElseThrow();
		
		
		
		existingCompany.setEmployeerName(updatedComapny.getEmployeerName());
		existingCompany.setWebsite(updatedComapny.getWebsite());
		existingCompany.setContact(updatedComapny.getContact());
		existingCompany.setEmail(updatedComapny.getEmail());
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
	
	
	
	@GetMapping("/getAllJobs/{employeerId}")
	public List<Job> getAllJobsPostedByCompany(@PathVariable Integer employeerId){
		List<Job> allJobs=jobRepo.findAllJobsByComapanyId(employeerId);
		if(allJobs.isEmpty())
		{
			throw new ResourceNotFoundException("No Job posted by this comapny");
		}
		return allJobs;
	}
	
	
	@PostMapping("/createJob")
	public String getAllCompanies( @RequestBody Job newJob) {
		
		Optional<Job> job=jobRepo.findById(newJob.getJobId());
		if(job.isPresent()) {
			throw new ResourceNotFoundException("Job already created which this job id");
		}
		jobRepo.save(newJob);
		return "Job Posted Successfully";
	}
	
	
	@PostMapping("/updateJob")
	public String updateJobInfo( @RequestBody Job updatedJob) {
		
		Job existingJob = jobRepo.findById(updatedJob.getJobId()).orElseThrow();
		// Update fields
		existingJob.setJob_title(updatedJob.getJob_title());
		existingJob.setJob_discription(updatedJob.getJob_discription());
		existingJob.setStatus(updatedJob.getStatus());
	    jobRepo.save(updatedJob);
	    return "JOb updated successfully";	
		
	}
	
//	@PostMapping("/updateJobStatus")
//	public String updateJobStatus( @RequestBody Job newJob) {
//		
//		
////		List<Employeer> companyies=employeerRepo.findAll();
//		jobRepo.save(newJob);
//		
//		return "Job Created Successfully";
//		
//	}
	
	
	@DeleteMapping("/job/{jobId}")
	public String deleteJob( @PathVariable Integer jobId) {
		Job job = jobRepo.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job not found"));  
		jobRepo.delete(job);
	    return "Job Deleted successfully";	
	}
	
	
	
//============== User-Job Application releted operations by company=================
	
	@PatchMapping("/applyJobStatus/{applyid}/{status}")
	public String changeApplyJobStatus(@PathVariable Integer applyId,@PathVariable String Status) {
		ApplyJob application=applyJobRepo.findById(applyId).orElseThrow(()-> new ResourceNotFoundException("Job application not found with this ID"));
		application.setStatus(Status);
		applyJobRepo.save(application);
		return "Job Status Updated Successfully";
	}
	
	@GetMapping("/getAllApplicant/{jobId}")
	public List<ApplyJob> getAllApplicant(@PathVariable Integer jobId){
		 List<ApplyJob> allApplicantOfPerticularJob=applyJobRepo.findAllApplicantByJobId(jobId);
		 return allApplicantOfPerticularJob;
	}
	
//============== User operations by company=================
	
	@GetMapping("/getUser/{userId}")
	public User getPerticularUser(@PathVariable Integer id) {
		return userService.getUserByUserId(id);
	}
	
	@GetMapping("resume/{userId}")
	public ResponseEntity<org.springframework.core.io.Resource> getResume(@PathVariable Integer userId) throws IOException{
			return fileService.getResume(userId);
	}
	
	//create a function which will return of users by search for particular skill
	
	
	
	
	
	
	

}
