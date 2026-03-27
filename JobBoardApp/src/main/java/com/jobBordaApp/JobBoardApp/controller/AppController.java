package com.jobBordaApp.JobBoardApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;

@RestController
//@RequestMapping()
public class AppController {
	
	@Autowired
	UserRepo userRepo;   //Reporisotory variable
	
	@Autowired
	JobRepo jobRepo;
	
	@Autowired
	EmployeerRepo employeerRepo;
	
	
//	Apply for the job
//	@PostMapping("/applyJob")
//	public String userApplyForJob(@RequestBody Job ) {
//		return "applied";
//	}
//	
	
	
	@GetMapping("/viewAllJobs")
	public List<Job> getAllJobs(){
		
		List<Job> allJobs= jobRepo.findAll();
		return allJobs;
		
	}
	
	@GetMapping("/allJobs/{employeerId}")
	public List<Job> getPerticularCompanyJobs(@PathVariable int companyId){
		List<Job> allJobs= jobRepo.findJobsByCompanyId(companyId);
//		if(allJobs.size()==0) {
//			System.out.println("No jobs found with comapny Id: "+companyId);
//			return allJobs;
//		}
//		else
//			return allJobs;
		return allJobs;
		
	}

}
