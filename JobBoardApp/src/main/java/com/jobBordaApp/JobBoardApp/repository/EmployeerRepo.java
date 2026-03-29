package com.jobBordaApp.JobBoardApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.User;


public interface EmployeerRepo  extends JpaRepository<Employeer,Integer> {

	
	public Optional<Employeer> findByEmail(String email); 
}
