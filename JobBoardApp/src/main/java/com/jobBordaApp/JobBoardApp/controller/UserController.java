package com.jobBordaApp.JobBoardApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.dto.ChangePasswordDTO;
import com.jobBordaApp.JobBoardApp.dto.LoginDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;
import com.jobBordaApp.JobBoardApp.service.FileService;
import com.jobBordaApp.JobBoardApp.service.UserService;


@RestController
//@RequestMapping("/jobBoardApp/user")
public class UserController {
	
	
	
//	user Object
//	
//	{
//		"first_name":"Tushar",
//		"last_name":"Patil",
//		"mobile_no":9561500379,
//		"email":"tusharpatil2181@gmail.com",
//		"password":123,
//		"education":"Msc",
//		"skills":"java"
//		
//	}
	
	
	
	@GetMapping("/testUser")
	public String testProj() {
		
		return "User Run successfully";
	}
	
	
	@Autowired
	private UserRepo userRepo;   //Reporisotory variable
	
	@Autowired
	private UserService userService;   //Reporisotory variable
	
	@Autowired
	private ApplyJobRepo applicationRepo;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ApplyJobRepo applyJobRepo;
	
	
	
//================User CURD operation========================

	
	@PostMapping("/register")
	public String registerUser(@RequestBody User newUser) {
		
		Optional<User> existingUser=userRepo.findByEmail(newUser.getEmail());
		if(existingUser.isPresent()) {
//			throw new RuntimeException("User already exist");
			return "User already exist";
		}
		userRepo.save(newUser);
		return "User Created Successfully";	
	}
	
	
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginDTO request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResourceNotFoundException( "Invalid password");
        }
		return "user login successfully";
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		if(allUsers.size()==0) {
			throw new ResourceNotFoundException("User List is empty");
		}
		return allUsers;
	}
	
	
//User Service Implemented	
	@GetMapping("user/{id}")
	public User getPerticularUser(@PathVariable Integer id) {
		return userService.getUserByUserId(id);
	}
	
	
	
	@PutMapping("/user")
	public String updateUserInfo(@RequestBody User updatedUser) {
		 
		User existingUser = userRepo.findById(updatedUser.getUserId()).orElseThrow();
		// Update fields
	    existingUser.setFirst_name(updatedUser.getFirst_name());
	    existingUser.setLast_name(updatedUser.getLast_name());
	    existingUser.setMobNo(updatedUser.getMobNo());
	    existingUser.setEmail(updatedUser.getEmail());
	    existingUser.setPassword(updatedUser.getPassword());
	    existingUser.setEducation(updatedUser.getEducation());
	    existingUser.setSkills(updatedUser.getSkills());
	    
	    userRepo.save(existingUser);
	    return "User updated successfully";		
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable Integer id) {
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));  
		userRepo.delete(user);
	    return "User Deleted successfully";		
	}
	
	
	@PatchMapping("/changePass/{id}")
	public String changePassword(@PathVariable Integer id, @RequestBody ChangePasswordDTO newPass) {
		
		User usr=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
		
		//Forntend vaidation 
//		1 old pass is not same to new pass
//		2 new pass and conform pass should be match
		if(!newPass.getCurrentPass().equals(usr.getPassword())){
			throw new ResourceNotFoundException("Your current password is not matching");
		}
		usr.setPassword(newPass.getNewPass());
		userRepo.save(usr);
		
		return "Password Changed";
		
	}
	
	@PatchMapping("/user/{id}")
	public User updatePartial(@PathVariable Integer id, @RequestBody User updatedUser) {

	    User existingUser = userRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    if (updatedUser.getFirst_name() != null)
	        existingUser.setFirst_name(updatedUser.getFirst_name());

	    if (updatedUser.getLast_name() != null)
	        existingUser.setLast_name(updatedUser.getLast_name());

	    if (updatedUser.getMobNo() != null)
	        existingUser.setMobNo(updatedUser.getMobNo());

	    if (updatedUser.getEmail() != null)
	        existingUser.setEmail(updatedUser.getEmail());

	    if (updatedUser.getPassword() != null)
	        existingUser.setPassword(updatedUser.getPassword());

	    if (updatedUser.getEducation() != null)
	        existingUser.setEducation(updatedUser.getEducation());

	    if (updatedUser.getSkills() != null)
	        existingUser.setSkills(updatedUser.getSkills());

	    return userRepo.save(existingUser);
	}
	


	    @PostMapping("/uploadResume/{userId}")
	    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer userId) {
	    	
	        try {
	            String path = fileService.uploadOrUpdateResume(userId,file);
	            return "File uploaded successfully at: " + path;

	        } catch (Exception e) {
	            return "File upload failed: " + e.getMessage();
	        }
	    }
	    
	    
	    
	    @GetMapping("resume/{userId}")
		public ResponseEntity<org.springframework.core.io.Resource> getUserResume(@PathVariable Integer userId) throws IOException{
				return fileService.getResume(userId);
		}
	    
	    
	    
		@GetMapping("user/{id}/applications")
		public List<ApplyJob> getAllApplicationOfPerticularUser(@PathVariable Integer userId) {
			
			List<ApplyJob> allApplications= applyJobRepo.findAllApplicationsByUserId(userId);
			if(allApplications.isEmpty()) {
				throw new ResourceNotFoundException("Not appied for any job");
			}
			return allApplications;
		}
		
	
	    
//================================User Services End========================================
	    

}
