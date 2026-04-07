package com.jobBordaApp.JobBoardApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public User getUserByUserId(@PathVariable Integer id) {
			User user= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with this ID"));
			return user;
	}
		
	

}
