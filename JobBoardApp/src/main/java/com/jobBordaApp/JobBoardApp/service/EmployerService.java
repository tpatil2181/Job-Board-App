package com.jobBordaApp.JobBoardApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;

@Service
public class EmployerService {
	
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	
	
	
//-----------------------------JOB----------------------------------------
	
	public ResponseEntity<?> CreateNewJob(@RequestBody Job newJob){
		Optional<Job> job=jobRepo.findById(newJob.getJobId());
		if(job.isPresent()) {
			throw new ResourceNotFoundException("Job already created which this job id");
		}
		jobRepo.save(newJob);
		return ResponseEntity.ok(Map.of("message", "Job Created successfully"));	
	}
	
	
	public ResponseEntity<?> DeleteExistingJob(@PathVariable Integer jobId,@PathVariable Integer EmployeerId ){
		
			Optional<Job> job =jobRepo.findById(jobId);
			
			if(job.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job Not Found"));
			}
			Job jb=job.get();
			Optional<Employeer> emplr=employeerRepo.findById(EmployeerId);
			if(emplr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Employeer Not Found"));
			}
			jobRepo.delete(jb);
			return ResponseEntity.ok(Map.of("message", "Job Deleted successfully"));	
	}
	
	

	public ResponseEntity<?> updateJob(@PathVariable Integer id,@RequestBody Job updatedJob) {

	    Job existingJob = jobRepo.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("Job not found"));

	    if (updatedJob.getJobTitle() != null)
	        existingJob.setJobTitle(updatedJob.getJobTitle());

	    if (updatedJob.getEmployer() != null)
	        existingJob.setEmployer(updatedJob.getEmployer());

	    if (updatedJob.getWorkMode() != null)
	        existingJob.setWorkMode(updatedJob.getWorkMode());

	    if (updatedJob.getJobLocation() != null)
	        existingJob.setJobLocation(updatedJob.getJobLocation());

	    if (updatedJob.getMinExperience() != null)
	        existingJob.setMinExperience(updatedJob.getMinExperience());

	    if (updatedJob.getMaxExperience() != null)
	        existingJob.setMaxExperience(updatedJob.getMaxExperience());

	    if (updatedJob.getMinSalary() != null)
	        existingJob.setMinSalary(updatedJob.getMinSalary());

	    if (updatedJob.getMaxSalary() != null)
	        existingJob.setMaxSalary(updatedJob.getMaxSalary());

	    if (updatedJob.getNoOfOpenings() != null)
	        existingJob.setNoOfOpenings(updatedJob.getNoOfOpenings());

	    if (updatedJob.getJobDescription() != null)
	        existingJob.setJobDescription(updatedJob.getJobDescription());

	    if (updatedJob.getRole() != null)
	        existingJob.setRole(updatedJob.getRole());

	    if (updatedJob.getEmploymentType() != null)
	        existingJob.setEmploymentType(updatedJob.getEmploymentType());

	    if (updatedJob.getIndustryType() != null)
	        existingJob.setIndustryType(updatedJob.getIndustryType());

	    if (updatedJob.getEducations() != null)
	        existingJob.setEducations(updatedJob.getEducations());

	    if (updatedJob.getSkills() != null)
	        existingJob.setSkills(updatedJob.getSkills());

	    if (updatedJob.getStatus() != null)
	        existingJob.setStatus(updatedJob.getStatus());

	    jobRepo.save(existingJob);

	    return ResponseEntity.ok(
	            Map.of(
	                    "message",
	                    "Job updated successfully"
	            ));
	}
	
	
	
		public ResponseEntity<?> getAllApplicantByJob(@PathVariable Integer jobId) {
			
			Optional<Job> job =jobRepo.findById(jobId);
			
			if(job.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job Not Found"));
			}
			 List<ApplyJob> applnList=applyJobRepo.findAllApplicantByJobId(jobId);
			if(applnList.size()==0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Np Applications Yet"));
			}
			
			return ResponseEntity.ok(applnList);

		}
		
		
		public ResponseEntity<?> getAllJobsPostedByCompany(@PathVariable Integer employeerId){
			
			Optional<Employeer> emplr=employeerRepo.findById(employeerId);
			if(emplr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Employeer Not Found"));
			}
			List<Job> allJobs=jobRepo.findAllJobsByComapanyId(employeerId);
			if(allJobs.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","No Job posted yet"));
			}
			return ResponseEntity.ok(allJobs);
		}

}
