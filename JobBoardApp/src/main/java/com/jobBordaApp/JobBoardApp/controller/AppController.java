package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;
import com.jobBordaApp.JobBoardApp.service.FileService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping()
public class AppController {
	
	@Autowired
	UserRepo userRepo;   //Reporisotory variable
	
	@Autowired
	JobRepo jobRepo;
	
	@Autowired
	EmployeerRepo employeerRepo;
	
	@Autowired
	ApplyJobRepo applicationRepo;
	
	 @Autowired
	 private FileService fileService;
	
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
	
	
	@GetMapping("/viewAllJobs")
	public List<Job> getAllJobs(){
		
		List<Job> allJobs= jobRepo.findAll();
		return allJobs;
		
	}
	
	
	@GetMapping("allAppliedJob/{userID}")
	public List<ApplyJob> getListOfAllJobdAppliedByUser(@PathVariable Integer userId){
		
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		List<ApplyJob> allJobOfUser=applicationRepo.findAllApplicationsByUserId(userId);
		if(allJobOfUser.size()==0) {
			throw new ResourceNotFoundException("No jobs applied by this user");
		}
		return allJobOfUser;
		
	}
	
	
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
	public String applyJob(@RequestBody ApplyJob newApplication) {

	    Integer userId = newApplication.getUser().getUserId();
	    Integer jobId = newApplication.getJob().getJobId();

	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new RuntimeException("Job not found"));

	    newApplication.setUser(user);  // ✅ managed entity
	    newApplication.setJob(job);    // ✅ managed entity

	    applicationRepo.save(newApplication);

	    return "Applied Successfully";
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
