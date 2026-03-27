package com.jobBordaApp.JobBoardApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.dto.UserLoginDTO;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;


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
	
	
	
//================User CURD operation========================
	
	@PostMapping("/login/")
	public String userLogin(@RequestBody UserLoginDTO request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
		return "user login successfully";
	}
	
	@GetMapping("getUser/{id}")
	public User getPerticularUser(@PathVariable Integer id) {
		User user= userRepo.findById(id).orElseThrow();
		return user;
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
	
//	@GetMapping("user/{id}/applications")
//	public void getAllApplicationOfPerticularUser(@PathVariable Integer id) {
//		User user= userRepo.findById(id).orElseThrow();
//		user
//		
//		return user;
//	}
	
	
//================User CURD operation========================
	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
//		if(allUser)
		return allUsers;
	}
	
	
	@PostMapping("/userRegistration")
	public String registerUser(@RequestBody User newUser) {
		userRepo.save(newUser);
		return "User Created Successfully";	
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
		User user = userRepo.findById(id).orElseThrow();  
		userRepo.delete(user);

	    return "User Deleted successfully";		
	}
		

}
