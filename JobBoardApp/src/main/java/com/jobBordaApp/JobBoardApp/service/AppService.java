package com.jobBordaApp.JobBoardApp.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateRegisterDTO;
import com.jobBordaApp.JobBoardApp.dto.EmployerRegisterDTO;
import com.jobBordaApp.JobBoardApp.dto.JobDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginResponseDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.AppUser;
import com.jobBordaApp.JobBoardApp.enums.Role;
import com.jobBordaApp.JobBoardApp.exception.RecordAvailableException;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.mapper.CandidateMapper;
import com.jobBordaApp.JobBoardApp.mapper.JobMapper;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.AppUserRepo;

@Service
public class AppService {
	
	@Autowired
	private CandidateRepo candidateRepo;   //Reporisotory variable
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private EmployeerRepo employeerRepo;
	
	@Autowired
	private ApplyJobRepo applicationRepo;
	
	@Autowired
	private AppUserRepo userRepo;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private JobMapper jobMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	 
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); 
	 

//----------------------------Login And Registration----------------------------	
	
		public ResponseEntity<?> registerCandidate(@RequestBody CandidateRegisterDTO dto) {
	
		    if (userRepo.findByEmail(dto.getEmail()) != null) {
		    	
		        return ResponseEntity.badRequest().body(Map.of("message","Email already exists"));
		    }
	
		    AppUser user = AppUser.builder()
		            .email(dto.getEmail())
		            .password(encoder.encode(dto.getPassword()))
		            .role(Role.CANDIDATE)
		            .build();
	
		    Candidate candidate = Candidate.builder()
		            .firstName(dto.getFirstName())
		            .lastName(dto.getLastName())
		            .mobNo(dto.getMobNo())
		            .user(user)
		            .build();
		    candidateRepo.save(candidate);
	
		    return ResponseEntity.ok(Map.of("message","Candidate registered successfully"));
		}
		
		
	
		public ResponseEntity<?> employerRegister(@RequestBody EmployerRegisterDTO dto) {
		
			if (userRepo.findByEmail(dto.getEmail()) != null) {
		    	
		        return ResponseEntity.badRequest().body(Map.of("message","Email already exists"));
		    }
	
		    AppUser user = AppUser.builder()
		            .email(dto.getEmail())
		            .password(encoder.encode(dto.getPassword()))
		            .role(Role.EMPLOYER)
		            .build();
	
		    Employeer employeer = Employeer.builder()
		            .employeerName(dto.getEmployerName())
		            .contact(dto.getContact())
		            .website(dto.getWebsite())
		            .user(user)
		            .build();
		    
		    employeerRepo.save(employeer);
		    
		    return ResponseEntity.ok(Map.of("message","Employeer registered successfully"));
 
		}
		
		
		
		
		public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

		    Authentication auth = authenticationManager.authenticate(
		            new UsernamePasswordAuthenticationToken(
		                    loginDTO.getEmail(),
		                    loginDTO.getPassword()));

		    if (auth.isAuthenticated()) {

		        AppUser user = userRepo.findByEmail(loginDTO.getEmail());

		        LoginResponseDTO response = LoginResponseDTO.builder()
		                .token(jwtService.generateToken(user.getEmail()))
		                .role(user.getRole().name())
		                .userId(user.getUserId())
		                .email(user.getEmail())
		                .build();

		        return ResponseEntity.ok(response);
		    }

		    return ResponseEntity.badRequest().body(Map.of("message", "fail"));
		}
		
//		public String verifyAndLogin( @RequestBody LoginDTO loginDTO) {
//
//		    Authentication auth =
//		            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
//
//		    if (auth.isAuthenticated()) {
//		    	
//		    	return jwtService.generateToken(loginDTO.getEmail());
////		        return ResponseEntity.ok(Map.of("message", "success"));
//		    }
//		    return "fail";
////		    return ResponseEntity.badRequest().body(Map.of("message", "fail"));
//		}
		
//		public ResponseEntity<?> verify(User user) {
//		
//		Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//		if(auth.isAuthenticated()) {
//			 return ResponseEntity.ok(Map.of("message", "success" ));
//		}
//    	return ResponseEntity.badRequest().body(Map.of( "message", "Fail "));
//	}
	
	
//===========================Candidate Specific service ==========================================
	 
//	
//		public ResponseEntity<?> registerCandidate(@RequestBody Candidate newCandidate) {
//			
//			Candidate existingCandidate=candidateRepo.findByEmail(newCandidate.getEmail());
//			if(existingCandidate!=null) {
//				 return ResponseEntity.badRequest().body(Map.of("message", "Candidate already exist"));
//				}
//						newCandidate.setPassword(encoder.encode(newCandidate.getPassword()));
//				candidateRepo.save(newCandidate);	
//			 return ResponseEntity.ok(Map.of("message", "Candidate Registered Successfully")); 
//		}
		
		
//Applied Role Base Autherization , this registration method set candidate role as a Condidate for limited autharization in code
		
		
		
		
		
//		Implemented Spring security and password encoder decoder and the following request by spring security and authent
		
//		@GetMapping("/profile")//(Login)
//		public ResponseEntity<?> getProfile(Authentication authentication) {
//
//		    String email = authentication.getName();
//		    Candidate candidate = candidateRepo.findByEmail(email);
//		    CandidateDTO candidateDTO =candidateMapper.mapCandidateToCandidateDTO(candidate);
//
//		    return ResponseEntity.ok(candidateDTO);
//		}
		
		
//	
//		public ResponseEntity<?> candidateLogin(@RequestBody LoginDTO request) {
//				Candidate candidate = candidateRepo.findByEmail(request.getEmail());
//				if(candidate==null) 
//					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
//	
//				if (!candidate.getPassword().equals(request.getPassword()))
//					return ResponseEntity.badRequest().body(Map.of( "message", "Invalid password"));
//	
//				CandidateDTO candidateDto=candidateMapper.mapCandidateToCandidateDTO(candidate);	
//					return ResponseEntity.ok(candidateDto);
//			//	    return ResponseEntity.ok(Map.of("message", "Candidate login successfully" ));
//			}
	
	
	
	 

		
		

		
	
//-------------------------------------------------------------------------
	
//	Only employeer can get any candidate so move this in employeer
		
//	@GetMapping("/candidate/{email}")
//	public ResponseEntity<?> getCandidate(@PathVariable String email) {
//
//		    Candidate existingCandidate = candidateRepo.findByEmail(email);
//		    
//		    if (existingCandidate==null) {
//		        return ResponseEntity
//		                .status(HttpStatus.NOT_FOUND)
//		                .body("Candidate not found");
//		    }
//		    
////		    Candidate candidate = existingCandidate();
//		    CandidateDTO dto = new CandidateDTO();
//		    
//	//	    dto.setCandidateId(candidate.getCandidateId());
////		    dto.setFirst_name(candidate.getFirstName());
////		    dto.setLast_name(candidate.getLastName());
////		    dto.setMobNo(candidate.getMobNo());
////		    dto.setEmail(candidate.getEmail());
////		    dto.setEducation(candidate.getEducation());
//	//	    dto.setResume(candidate.getResume());
////		    dto.setSkills(candidate.getSkills());
//	
//	
//		    return ResponseEntity.ok(dto);
//	}
	
	 
//===========================Candidate Specific service End ==========================================
	
	

	
//===========================Company(Employer) Specific service ==========================================


//Implememted Role base Registration to implement role based autharization	
	
	
//	
//	public ResponseEntity<?> employerRegister(@RequestBody Employeer employeer) {
//		  return ResponseEntity.ok(Map.of("message","Candidate registered successfully"));
//			
//			Employeer existingCompany= employeerRepo.findByEmail(employeer.getEmail());
//			if(existingCompany==null) {
//				throw new RuntimeException("Company already Register");
//			}
//			employeerRepo.save(company);
//			return ResponseEntity.ok(Map.of("message", "Company Registered Successfully" ))
//	}
	
	
	
	
//	Implemented Spring security and password encoder decoder and the following request by spring security and authent
	
//	ALSO IMPLEMENTE SPRING SECURITY FOR COMPANY 
	
//	@GetMapping("/profile")(Login)
//	public ResponseEntity<?> getProfile(Authentication authentication) {
//
//	    String email = authentication.getName();
//	    Candidate candidate = candidateRepo.findByEmail(email);
//	    CandidateDTO candidateDTO =candidateMapper.mapCandidateToCandidateDTO(candidate);
//
//	    return ResponseEntity.ok(candidateDTO);
//	}
	
	


	public ResponseEntity<?> employerLogin(@RequestBody LoginDTO request  ) {
		
		return ResponseEntity.badRequest().body(Map.of( "message", "Company login OK"));
		
//		Employeer company = employeerRepo.findByEmail(request.getEmail());
//			if(company==null) 
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
//
//	        if (!company.getPassword().equals(request.getPassword())) {
//	        	return ResponseEntity.badRequest().body(Map.of( "message", "Invalid password"));
//	        }
//	        
//	        CandidateDTO candidateDto=candidateMapper.mapCandidateToCandidateDTO(company);
//	        
//			return ResponseEntity.ok(candidateDto);
//			return "Company login successfully";
	}
	
//===========================Company(Employer) Specific service ==========================================
	
	

	
	
	
//===========================Main service of job board app Specific service ==========================================
	
	
	
	
	public ResponseEntity<?> JobApplication(@RequestBody  ApplyJob newApplication) { 
		
			Integer candidateId = newApplication.getCandidate().getCandidateId();
			Integer jobId = newApplication.getJob().getJobId();
			
			Optional<Candidate> candidate = candidateRepo.findById(candidateId);
				if(candidate.isEmpty()) 
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Candidate Not Found"));
			
			Candidate existingCandidate = candidate.get();
			
			Optional<Job> job =jobRepo.findById(jobId);
				if(job.isEmpty()) 
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Job not found"));
			
			Job existingJob = job.get();
			
			Optional<ApplyJob> isApplied=applicationRepo.findByUserIdAndJobId(candidateId,jobId);
		    
			    if(isApplied.isPresent()) 
			    	return ResponseEntity.badRequest().body(Map.of( "message", "You have already applied for this job"));
	
			
			 newApplication.setCandidate(existingCandidate); 
			 newApplication.setJob(existingJob);   
			 
			 applicationRepo.save(newApplication);
			 
			 return ResponseEntity.ok(Map.of("message", "Apply for job successfully" ));

			}





	

		
}
