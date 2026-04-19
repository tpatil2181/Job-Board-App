package com.jobBordaApp.JobBoardApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.service.JobService;




@RestController
public class JobController {
	
	
	
	
//	in advance apply filtering on job
	
	@Autowired
	private JobService jobservice;
	
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
	
	
	
	
	
	
//	@RequestParam(required = false)String search
	
	@GetMapping("/allJobs")
	public List<Job> getAlljobs(@RequestParam(required = false, defaultValue = "1") int pageNo,
								@RequestParam(required = false, defaultValue = "5")  int pageSize,
								@RequestParam(required = false, defaultValue = "jobId") String sortBy,
								@RequestParam(required = false, defaultValue = "ASE")String sortDir,
								@RequestParam(required = false)Integer jobId,
								@RequestParam(required = false)String employer,
								@RequestParam(required = false)String jobTitle,
								@RequestParam(required = false)String status,
								@RequestParam(required = false)String createDate){
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("ASE")) {
			sort=Sort.by(sortBy).ascending();
			
		}else {
			sort=Sort.by(sortBy).descending();	
		}
		
		return jobservice.findAllJobs(PageRequest.of(pageNo-1, pageSize,sort),jobId,employer,jobTitle,status,createDate);
		
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
