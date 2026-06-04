package com.jobBordaApp.JobBoardApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobBordaApp.JobBoardApp.entity.AppUser;


public interface AppUserRepo extends JpaRepository<AppUser,Integer> {

	AppUser findByEmail(String email);
	
	

}
