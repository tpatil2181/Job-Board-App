package com.jobBordaApp.JobBoardApp.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobBordaApp.JobBoardApp.entity.User;


@Repository
public interface UserRepo  extends JpaRepository<User,Integer> {
	
	public Optional<User> findByEmail(String email);
	
//	public User findByUsername(String username);
//	
//	@Query()
//	public List<Job> findAllapplicationOfPerticlarUser();

}
