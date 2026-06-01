package com.jobBordaApp.JobBoardApp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.specification.JobSpecification;

@Service
public class JobService {
	

	@Autowired
	JobRepo jobRepo;
	
	
	
	public ResponseEntity<?> getJob(@PathVariable Integer jobId){
		
		Optional<Job> job =jobRepo.findById(jobId);
		
		if(job.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job Not Found"));
		}
		Job jb=job.get();
		return ResponseEntity.ok(jb);	

	}
	
	
	
	          
	public List<Job> findAllJobs(Pageable pageable, Integer jobId,String employer,String jobTitle,String status, String createDate){
		
		Specification<Job> spec= JobSpecification.getJobSpecification(jobId,employer,jobTitle,status,createDate);
		return jobRepo.findAll(spec,pageable).getContent();
		
//		if(search==null) {
//			return jobRepo.findAll(pageable).getContent();
//			
//		}else {
//			return jobRepo.findByJobTitle(search,pageable).getContent();
//		}
		
	}
	
	

	

}
