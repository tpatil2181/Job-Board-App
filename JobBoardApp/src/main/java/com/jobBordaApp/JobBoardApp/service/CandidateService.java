package com.jobBordaApp.JobBoardApp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.dto.ApplyJobDTO;
import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;
import com.jobBordaApp.JobBoardApp.entity.CandidateExperience;
import com.jobBordaApp.JobBoardApp.entity.CandidateResume;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateCertificationsRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateEducationRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateExperienceRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateResumeReop;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepo candidateRepo;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private CandidateEducationRepo candidateEducationRepo;
	
	@Autowired
	private CandidateCertificationsRepo candidateCertificationRepo;
	
	@Autowired
	private CandidateExperienceRepo candidateExperienceRepo;
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	@Autowired
	private CandidateResumeReop candidateResumeRepo;
	
	@Autowired
	private FileService fileService;
	
	
	
//---------------------------------------------Candidate-----------------------------------------------------------	
	
	
	public ResponseEntity<?> getCandidateByCandidateId(@PathVariable Integer id) {
		Optional<Candidate> candidate= candidateRepo.findById(id);
		
		if(candidate.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
			
		}
		Candidate Cnd=candidate.get();
		
		return ResponseEntity.ok(Cnd);	
	}
	
	
	public ResponseEntity<?> updateCandidate(@PathVariable Integer id, @RequestBody Candidate updatedCandidate) {

		Candidate existingCandidate = candidateRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    if (updatedCandidate.getFirstName() != null)
	        existingCandidate.setFirstName(updatedCandidate.getFirstName());

	    if (updatedCandidate.getLastName() != null)
	        existingCandidate.setLastName(updatedCandidate.getLastName());
	    
	    if (updatedCandidate.getCandidateTitle() != null)
	        existingCandidate.setCandidateTitle(updatedCandidate.getCandidateTitle());
	    
	    if (updatedCandidate.getCandidateAbout() != null)
	        existingCandidate.setCandidateAbout(updatedCandidate.getCandidateAbout());

	    if (updatedCandidate.getMobNo() != null)
	        existingCandidate.setMobNo(updatedCandidate.getMobNo());

	    if (updatedCandidate.getEmail() != null)
	        existingCandidate.setEmail(updatedCandidate.getEmail());

	    if (updatedCandidate.getPassword() != null)
	        existingCandidate.setPassword(updatedCandidate.getPassword());

//	    if (updatedCandidate.getEducation() != null)
//	        existingCandidate.setEducation(updatedCandidate.getEducation());

	    if (updatedCandidate.getSkills() != null)
	        existingCandidate.setSkills(updatedCandidate.getSkills());
	    
		return ResponseEntity.ok(Map.of("message", "Candidate updated successfully"));	
	}

	
	
	
	public ResponseEntity<?> deleteCandidate(@PathVariable Integer id){
		Optional<Candidate> candidate = candidateRepo.findById(id);
		if(candidate.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
		}
		Candidate cand=candidate.get();
		candidateRepo.delete(cand);
		return ResponseEntity.ok(Map.of("message", "Candidate Deleted successfully"));	
	}
	
	
	
	
	public ResponseEntity<?> changeCandiddatePassword(@RequestBody ChangePasswordDTO newPass){
		
		Optional<Candidate> candidate=candidateRepo.findById(newPass.getId());

		if(candidate.isEmpty())
			return ResponseEntity.badRequest().body(Map.of("message", "Candidate not found"));
		
		Candidate existingCandidate = candidate.get();


		if(!newPass.getCurrentPass().equals(existingCandidate.getPassword())){
			System.out.println("New pass current pass "+newPass.getCurrentPass());
			System.out.println("Existing pass current pass "+existingCandidate.getPassword());
			throw new ResourceNotFoundException("Your current password is not matching");
		}
		existingCandidate.setPassword(newPass.getNewPass());
		candidateRepo.save(existingCandidate);

		return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
		
		//Forntend vaidation 
		//1 old pass is not same to new pass
		//2 new pass and conform pass should be match
}
	
	
//---------------------------------------------Candidate Education----------------------------------------------	
	
		public ResponseEntity<?> addEducation(@PathVariable Integer candidateId, @RequestBody CandidateEducation education){
			
				Optional<Candidate> candidate =candidateRepo.findById(candidateId);
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				Candidate Cnd=candidate.get();
				education.setCandidate(Cnd);
			    candidateEducationRepo.save(education);
			    return ResponseEntity.ok("Education Added");
		}
		
		public ResponseEntity<?> getEducation(@PathVariable Integer candidateId, @PathVariable Integer EduId){
				
				Optional<Candidate> candidate =candidateRepo.findById(candidateId);
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				Optional<CandidateEducation> CndEdu=candidateEducationRepo.findById(EduId);
				if(CndEdu.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Education Not Found"));
				}
				CandidateEducation CE=CndEdu.get();			
	
				return ResponseEntity.ok(CE);		
			
		}
		
		public ResponseEntity<?> updateEducation(@PathVariable Integer candidateId, @RequestBody CandidateEducation updatedEducation){
			 Candidate candidate = candidateRepo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

			    CandidateEducation education = candidateEducationRepo.findById(updatedEducation.getEducationId()).orElseThrow(() -> new RuntimeException("Education not found"));

			    // Validation
			    if (!education.getCandidate().getCandidateId().equals(candidateId)) {
			        return ResponseEntity.badRequest().body("Education does not belong to this candidate");
			    }

			    education.setDegree(updatedEducation.getDegree());
			    education.setCollege(updatedEducation.getCollege());
			    education.setStartYear(updatedEducation.getStartYear());
			    education.setEndYear(updatedEducation.getEndYear());
			    education.setPercentage(updatedEducation.getPercentage());

			    candidateEducationRepo.save(education);

			    return ResponseEntity.ok("Education Updated Successfully");
		}
		
	
		public ResponseEntity<?> deleteEducation( @PathVariable Integer candidateId, @PathVariable Integer educationId) {

			    Candidate candidate = candidateRepo.findById(candidateId)
			            .orElseThrow(() -> new RuntimeException("Candidate not found"));
	
			    CandidateEducation education = candidateEducationRepo.findById(educationId)
			            .orElseThrow(() -> new RuntimeException("Education not found"));
	
			    candidate.getEducations().remove(education);
	
			    candidateRepo.save(candidate);
	
			    return ResponseEntity.ok("Education Deleted Successfully");
		}
		
		
//---------------------------------------------Candidate Certification----------------------------------------------	
		
		public ResponseEntity<?> addCertification( @PathVariable Integer candidateId, @RequestBody CandidateCertification certification) {

			    Candidate candidate =candidateRepo.findById(candidateId).orElseThrow(() ->new RuntimeException("Candidate not found"));
			    certification.setCandidate(candidate);
			    candidateCertificationRepo.save(certification);
			    return ResponseEntity.ok("Certification Added");
		}
		
		public ResponseEntity<?> getCertification(@PathVariable Integer candidateId, @PathVariable Integer CertificationId){
				
				Optional<Candidate> candidate =candidateRepo.findById(candidateId);
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				Optional<CandidateCertification> CndCer=candidateCertificationRepo.findById(CertificationId);
				if(CndCer.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Education Not Found"));
				}
				CandidateCertification CC=CndCer.get();			
	
				return ResponseEntity.ok(CC);		
			
		}
		
		public ResponseEntity<?> updateCertification(@PathVariable Integer candidateId, @RequestBody CandidateCertification updatedCedrtification){
				Candidate candidate = candidateRepo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
	
				CandidateCertification certification = candidateCertificationRepo.findById(updatedCedrtification.getCertificationId()).orElseThrow(() -> new RuntimeException("Education not found"));

			    // Validation
			    if (!certification.getCandidate().getCandidateId().equals(candidateId)) {
			        return ResponseEntity.badRequest().body("Education does not belong to this candidate");
			    }

			    certification.setCertificateName(updatedCedrtification.getCertificateName());
//			    certification.setCollege(updatedCedrtification.ge());
//			    certification.setStartYear(updatedEducation.getStartYear());
	

			    candidateCertificationRepo.save(certification);

			    return ResponseEntity.ok("Certification Updated Successfully");
		}
		
		public ResponseEntity<?> deleteCertification( @PathVariable Integer candidateId,@PathVariable Integer certificationId) {
	
			    Candidate candidate = candidateRepo.findById(candidateId)
			            .orElseThrow(() -> new RuntimeException("Candidate not found"));
	
			    CandidateCertification CandidateCertification = candidateCertificationRepo.findById(certificationId)
			            .orElseThrow(() -> new RuntimeException("Education not found"));
	
			    candidate.getCertifications().remove(CandidateCertification);
	
			    candidateRepo.save(candidate);
	
			    return ResponseEntity.ok("Certification Deleted Successfully");
		}
	
	
//---------------------------------------------Candidate Experience----------------------------------------------
		
		
		public ResponseEntity<?> addExperience( @PathVariable Integer candidateId, @RequestBody CandidateExperience experience) {
			
			    Candidate candidate =candidateRepo.findById(candidateId).orElseThrow(() ->new RuntimeException("Candidate not found"));
			    experience.setCandidate(candidate);
			    candidateExperienceRepo.save(experience);
			    return ResponseEntity.ok("Experience Added");
		}
		
		public ResponseEntity<?> getExperience(@PathVariable Integer candidateId, @PathVariable Integer CertificationId){
			
				Optional<Candidate> candidate =candidateRepo.findById(candidateId);
				if(candidate.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
				}
				Optional<CandidateCertification> CndCer=candidateCertificationRepo.findById(CertificationId);
				if(CndCer.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Education Not Found"));
				}
				CandidateCertification CC=CndCer.get();			
	
				return ResponseEntity.ok(CC);		
		
		}
		
		public ResponseEntity<?> updateExperience(@PathVariable Integer candidateId, @RequestBody CandidateExperience updatedExperience){
			 	Candidate candidate = candidateRepo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

			 	CandidateExperience exp = candidateExperienceRepo.findById(updatedExperience.getCandExpId()).orElseThrow(() -> new RuntimeException("Education not found"));

			    // Validation
			    if (!exp.getCandidate().getCandidateId().equals(candidateId)) {
			        return ResponseEntity.badRequest().body("Experience does not belong to this candidate");
			    }

			    exp.setCompanyName(updatedExperience.getCompanyName());
			    exp.setJobTitle(updatedExperience.getJobTitle());
			    exp.setJoiningDate(updatedExperience.getJoiningDate());
			    exp.setEndingDate(updatedExperience.getEndingDate());
			    exp.setIsCurrentCompanny(updatedExperience.getIsCurrentCompanny());
			    exp.setAboutJobProfile(updatedExperience.getAboutJobProfile());

			    candidateExperienceRepo.save(exp);

			    return ResponseEntity.ok("Expericence Updated Successfully");
		}
		
		public ResponseEntity<?> deleteExperience( @PathVariable Integer candidateId,@PathVariable Integer experienceId) {
			
			    Candidate candidate = candidateRepo.findById(candidateId)
			            .orElseThrow(() -> new RuntimeException("Candidate not found"));
	
			    CandidateExperience candExp = candidateExperienceRepo.findById(experienceId)
			            .orElseThrow(() -> new RuntimeException("Experience not found"));
	
			    candidate.getExperiences().remove(candExp);
	
			    candidateRepo.save(candidate);
	
			    return ResponseEntity.ok("Certification Deleted Successfully");
		}
		
		
//---------------------------------------------Candidate Resume----------------------------------------------		

	
		public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file, @PathVariable Integer candidateId) {
			try {
	            int resumeId = fileService.uploadOrUpdateResume(candidateId,file);
	            Candidate cnd=candidateRepo.findById(candidateId).orElseThrow(()-> new ResourceNotFoundException("Candidate Not found with this ID"));
	            CandidateResume CR=candidateResumeRepo.findById(resumeId).orElseThrow(()-> new ResourceNotFoundException("Resume Not found with this ID"));;
	            cnd.setResume(CR);
	            candidateRepo.save(cnd);
	            return ResponseEntity.ok(Map.of("message", "Resume uploaded successfully at:" + CR.getPath()));
		
		        } catch (Exception e) {
		            return ResponseEntity.badRequest().body(Map.of(
						    "message",  "File upload failed: " + e.getMessage()
						));
		        }
		}
		
		
		public ResponseEntity<org.springframework.core.io.Resource> getCandidateResume(@PathVariable Integer resumeId) throws IOException{
			
				return fileService.getResume(resumeId);
			
		}
		
//---------------------------------------------Candidate Job----------------------------------------------	
		
		public ResponseEntity<?> jobApplication( @RequestBody ApplyJobDTO jobApplication ) {
			
				Optional<Employeer> emp= employeerRepo.findById(jobApplication.getEmployeerId()); 
				
				if(emp.isEmpty()) {
			        return ResponseEntity.badRequest().body("Employeer not found");						
				}
				Employeer emplor=emp.get();
				
				
				Optional<Job> job= jobRepo.findById(jobApplication.getEmployeerId()); 
				
				if(job.isEmpty()) {
			        return ResponseEntity.badRequest().body("Job not found");						
				}
				
				Job jb=job.get();
				
				Optional<Candidate> candidate = candidateRepo.findById(jobApplication.getCandidateId());
				
				if(candidate.isEmpty()) {
			        return ResponseEntity.badRequest().body("Candidate not found");						
				}
				Candidate cnd=candidate.get();
				
				
				Optional<ApplyJob> AppliedJob=applyJobRepo.getJobApplicationByEmpJobCndId(
																			jobApplication.getCandidateId(), jobApplication.getJobId(),
																			jobApplication.getEmployeerId());
				if(AppliedJob.isPresent()) {
					
			        return ResponseEntity.badRequest().body("You have already allied for the job");	
				}
				ApplyJob aj=AppliedJob.get();
				applyJobRepo.save(aj);
			    return ResponseEntity.ok("Job Applied");
		}
				
		public ResponseEntity<?> allAppliedJobs(@PathVariable Integer candidateId){
					
					Optional<Candidate> candidate = candidateRepo.findById(candidateId);
					
					if(candidate.isEmpty()) {
				        return ResponseEntity.badRequest().body("Candidate not found");						
					}
					
					List<ApplyJob> allJobOfCandidate=applyJobRepo.findAllApplicationsByCandidateId(candidateId);
					if(allJobOfCandidate.size()==0) {
						throw new ResourceNotFoundException("No jobs applied by this Candidate");
					}
					return ResponseEntity.ok(allJobOfCandidate);		

		}
		
		public ResponseEntity<?> withdarwJobApplication(@RequestBody ApplyJobDTO deleteJob){
			
			int rowsDeleted = applyJobRepo.deleteByCandidateIdAndJobId(deleteJob.getCandidateId(),deleteJob.getJobId());

			if (rowsDeleted == 0) {
			    return ResponseEntity.badRequest().body(Map.of("message","Application not found"));
			}

			return ResponseEntity.ok(Map.of("message", "Application withdrawn successfully"));
		}
	
		public ResponseEntity<?> getJobApplication(@RequestBody ApplyJobDTO getJobAppln){
			
			Optional<ApplyJob> AppliedJob=applyJobRepo.getJobApplicationByEmpJobCndId(
					getJobAppln.getCandidateId(), getJobAppln.getJobId(),
					getJobAppln.getEmployeerId());
					if(AppliedJob.isEmpty()) {
					
							return ResponseEntity.badRequest().body("Application Not found");	
					}
					ApplyJob aj=AppliedJob.get();
					applyJobRepo.save(aj);
					return ResponseEntity.ok(aj);
		}
	
	

	
		
	

}
