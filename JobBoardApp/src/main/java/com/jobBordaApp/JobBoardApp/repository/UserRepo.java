package com.jobBordaApp.JobBoardApp.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jobBordaApp.JobBoardApp.entity.User;


public interface UserRepo  extends JpaRepository<User,Integer> {
	
	public Optional<User> findByEmail(String email); 

}
