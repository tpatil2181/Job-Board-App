package com.jobBordaApp.JobBoardApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jobBordaApp.JobBoardApp.dto.AppliedJobDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangeJobStatusDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.dto.EmployeerDTO;
import com.jobBordaApp.JobBoardApp.dto.JobDTO;
import com.jobBordaApp.JobBoardApp.dto.PostedJobDTO;
import com.jobBordaApp.JobBoardApp.entity.AppUser;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.mapper.CandidateMapper;
import com.jobBordaApp.JobBoardApp.mapper.EmployerMapper;
import com.jobBordaApp.JobBoardApp.mapper.JobMapper;
import com.jobBordaApp.JobBoardApp.repository.AppUserRepo;
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
	
	@Autowired
	private AppUserRepo appUserRepo;
	
	@Autowired
	private EmployerMapper empMapper;
	
	@Autowired
	private JobMapper jobMapper;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); 


	
	
//-----------------------------Company Profile----------------------------------------	
	
	
	public ResponseEntity<?> getCompanyProfile(Authentication authentication ) {
		String email = authentication.getName();

		AppUser user = appUserRepo.findByEmail(email);

	    Optional<Employeer> Employer = employeerRepo.findByUser(user);
	    
		if(Employer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
		}
		Employeer emplor=Employer.get();
		
	    EmployeerDTO empDTO= empMapper.mapEmployerToEmployerDTO(emplor);
	    		  
	    return ResponseEntity.ok(empDTO);
	    
	}
	

	public ResponseEntity<?> updateEmployer(Employeer updatedEmployer, Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResponseEntity<?> deleteEmployer(Authentication authentication) {
		String email = authentication.getName();

	    AppUser user = appUserRepo.findByEmail(email);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "User not found"));
	    }

	    Employeer employer = employeerRepo.findByUser(user)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Employeer not found"));
	    
//	    befour deleting the employeer or candidate also delete it related field like job posted by the employer job appllied by candidate

	    employeerRepo.delete(employer);

	    // Optional: delete login account too
	    appUserRepo.delete(user);

	    return ResponseEntity.ok(
	            Map.of("message", "Candidate deleted successfully"));
	}


	public ResponseEntity<?> changeEmployerPassword(ChangePasswordDTO newPass, Authentication authentication) {
		String email = authentication.getName();

	    AppUser user = appUserRepo.findByEmail(email);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "User not found"));
	    }

	    // Verify current password
	    if (!passwordEncoder.matches(newPass.getCurrentPass(), user.getPassword())) {
	        return ResponseEntity.badRequest()
	                .body(Map.of("message", "Current password is incorrect"));
	    }

	    // Check if new password is same as current password
	    if (passwordEncoder.matches(newPass.getNewPass(), user.getPassword())) {
	        return ResponseEntity.badRequest()
	                .body(Map.of("message", "New password cannot be same as current password"));
	    }

	    // Update password
	    user.setPassword(passwordEncoder.encode(newPass.getNewPass()));
	    appUserRepo.save(user);

	    return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
	}

	
	
	
//-----------------------------JOB----------------------------------------
	
	
	public ResponseEntity<?> createNewJob(Job newJob,
            Authentication authentication) {

		String email = authentication.getName();
		
		AppUser user = appUserRepo.findByEmail(email);
		
		Employeer employer = employeerRepo.findByUser(user)
		.orElseThrow(() -> new RuntimeException("Employer not found"));
		
		newJob.setEmployer(employer);
		
		Job savedJob = jobRepo.save(newJob);
		
		return ResponseEntity.ok(Map.of("message", "Job Created successfully"));	
	}
	
	
//	public ResponseEntity<?> CreateNewJob(@RequestBody Job newJob){
////		Optional<Job> job=jobRepo.findById(newJob.getJobId());
////		if(job.isPresent()) {
////			throw new ResourceNotFoundException("Job already created which this job id");
////		}
//		jobRepo.save(newJob);
//		return ResponseEntity.ok(Map.of("message", "Job Created successfully"));	
//	}
	
	
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

//	    if (updatedJob.getSkills() != null)
//	        existingJob.setSkills(updatedJob.getSkills());

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
			List<PostedJobDTO> postedJobsDTO = allJobs.stream().map(jobMapper::JobTopostedJobDTO).toList();
		    
		    return ResponseEntity.ok(postedJobsDTO);		

//			return ResponseEntity.ok(allJobs);
		}

		
		public ResponseEntity<?> changeJobStatus(@PathVariable ChangeJobStatusDTO updatedStatus ){
			
			Optional<Job> job =jobRepo.findById(updatedStatus.getJobId());
			
			if(job.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job Not Found"));
			}
			Job jb=job.get();
			jb.setStatus(updatedStatus.getStatus());
			
			jobRepo.save(jb);
			
			 return ResponseEntity.ok(Map.of("message","Job updated successfully"));
			  
//		    return ResponseEntity.ok();		
					
		}

	



}
