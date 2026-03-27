package com.jobBordaApp.JobBoardApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;

@RestController
public class JobController {
	
	
	
	@Autowired
	JobRepo jobRepo;
	
	
	@PostMapping("/createJob")
	public String createJob(@RequestBody Job newJob) {
		jobRepo.save(newJob);
		return "Job created Successfully";
	}
	
	@PutMapping("/updateJob/{jobId}")
	public String updateJob(@RequestBody Job newJob) {
		jobRepo.save(newJob);
		return "Job created Successfully";
	}
	
	@DeleteMapping("/delJob/{jobId}")
	public String deleteJob(@RequestBody Job newJob) {
		jobRepo.save(newJob);
		return "Job created Successfully";
	}
	
	

}
