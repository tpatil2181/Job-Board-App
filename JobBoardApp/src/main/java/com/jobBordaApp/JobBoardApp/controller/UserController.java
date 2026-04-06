package com.jobBordaApp.JobBoardApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.ApplyJobRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;
import com.jobBordaApp.JobBoardApp.service.FileService;


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
	UserRepo userRepo;   //Reporisotory variable
	
	@Autowired
	ApplyJobRepo applicationRepo;
	
	 @Autowired
	 private FileService fileService;
	
	
	
//================User CURD operation========================

	
	@PostMapping("/registerUser")
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
	
	@GetMapping("user/{id}")
	public User getPerticularUser(@PathVariable Integer id) {
		User user= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with this ID"));
		return user;
	}
	
	@PutMapping("/user/{id}")
	public String updateUserInfo(@PathVariable Integer id,@RequestBody User updatedUser) {
		 
		User existingUser = userRepo.findById(id).orElseThrow();
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
	
	
	
	
	


	    @PostMapping("/upload/{userId}")
	    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer userId) {
	    	
	    	
	        try {
	            String path = fileService.uploadOrUpdateResume(userId,file);
	            return "File uploaded successfully at: " + path;

	        } catch (Exception e) {
	            return "File upload failed: " + e.getMessage();
	        }
	    }
	

	
// For update perticular field we can user @PatchMapping exlpore it and complete this mapping
	
//	@PutMapping("user/{id}/change-password")
//	public User changeUserPassword(@PathVariable Integer id) {
//		User user= userRepo.findById(id).orElseThrow();
//		user.getPassword();
//		
//		return user;
//	}
	

	
// Think about that file is comming form user resume file how it will be accepted in this mapping
	
//	@PostMapping("user/{id}/upload-resume")
//	public void  uploadResume(@PathVariable Integer id) {
//		Optional<User> user= userRepo.findById(id);
//		return user;
//	}
	

	
//Write a program to get all applications of the perticular user 
////	This should be in application controller
//	@GetMapping("user/{id}/applications")
//	public void getAllApplicationOfPerticularUser(@PathVariable Integer id) {
//		User user= userRepo.findById(id).orElseThrow();
////		user
//		
//		return user;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//================User CURD operation========================
	
	
//	@GetMapping("/users")
//	public List<User> getAllUsers() {
//		List<User> allUsers = userRepo.findAll();
////		if(allUser)
//		return allUsers;
//	}
	
//	
//	@PostMapping("/userRegistration")
//	public String registerUser(@RequestBody User newUser) {
//		userRepo.save(newUser);
//		return "User Created Successfully";	
//	}
//	
	
//	@PutMapping("/user/{id}")
//	public String updateUserInfo(@PathVariable Integer id,@RequestBody User updatedUser) {
//		 
//		User existingUser = userRepo.findById(id).orElseThrow();
//		
//		// Update fields
//	    existingUser.setFirst_name(updatedUser.getFirst_name());
//	    existingUser.setLast_name(updatedUser.getLast_name());
//	    existingUser.setMobNo(updatedUser.getMobNo());
//	    existingUser.setEmail(updatedUser.getEmail());
//	    existingUser.setPassword(updatedUser.getPassword());
//	    existingUser.setEducation(updatedUser.getEducation());
//	    existingUser.setSkills(updatedUser.getSkills());
//	    
//	    userRepo.save(existingUser);
//	    return "User updated successfully";		
//	}
//	
//	@DeleteMapping("/user/{id}")
//	public String deleteUser(@PathVariable Integer id) {
//		User user = userRepo.findById(id).orElseThrow();  
//		userRepo.delete(user);
//
//	    return "User Deleted successfully";		
//	}
//		

}
