package com.jobBordaApp.JobBoardApp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.specification.JobSpecification;

@Service
public class JobService {
	

	@Autowired
	JobRepo jobRepo;
	          
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
