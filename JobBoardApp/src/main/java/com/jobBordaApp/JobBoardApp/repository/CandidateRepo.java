package com.jobBordaApp.JobBoardApp.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobBordaApp.JobBoardApp.entity.Candidate;

@Repository
public interface CandidateRepo  extends JpaRepository<Candidate,Integer> {
	
	public Optional<Candidate> findByEmail(String email);
	

//	public User findByUsername(String username);
//	
//	@Query()
//	public List<Job> findAllapplicationOfPerticlarUser();
	
	
//========================Searching Operation========================
	
		//Get all user having pertucular skill
		

}
