package com.jobBordaApp.JobBoardApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;

@RestController
public class JobController {
	
	
//	in advance apply filtering on job
	
	
	@Autowired
	JobRepo jobRepo;
	
	
	@PostMapping("/job")
	public String createJob(@RequestBody Job newJob) {
		
//		Job job = jobRepo.findById(Jobid).orElseThrow(()-> new ResourceNotFoundException("Job not found with this if"));  
		jobRepo.save(newJob);
		return "Job created Successfully";
	}
	
	@PutMapping("/job/{jobId}")
	public String updateJob(@RequestBody Job newJob) {
		
		jobRepo.save(newJob);
		return "Job Updated Successfully";
	}
	
	@DeleteMapping("/job/{jobId}")
	public String deleteJob(@PathVariable Integer Jobid) {
		Job job = jobRepo.findById(Jobid).orElseThrow(()-> new ResourceNotFoundException("Job not found with this ID"));  
		jobRepo.delete(job);
	    return "Job Deleted successfully";		
	}
	
	
	@GetMapping("/allJobs")
	public List<Job> getAlljobs(){
		List<Job> allJobs=jobRepo.findAll();
		if(allJobs.size()==0) {
			  throw new ResourceNotFoundException( "No Jobs available"); 
		}
		return allJobs;
	}
	
	
	@GetMapping("/allJobs/{employeerId}")
	public List<Job> getAlljobs(@PathVariable Integer companyId){
		List<Job> allJobs=jobRepo.findAllJobsByComapanyId(companyId);
		if(allJobs.size()==0) {
			  throw new ResourceNotFoundException( "No Jobs available"); 
		}
		return allJobs;
	}
	
	@GetMapping("/job/{jobId}")
	public Job findPerticularJob(@PathVariable Integer JobId) {
		Job isJobExist=jobRepo.findById(JobId).orElseThrow(()-> new ResourceNotFoundException("No job found with this jobId"));
		return isJobExist; 
	}
	
	
	
	
//===================Advance Part=================	
	
// In advance create a get job on filtering	
	
	

}
