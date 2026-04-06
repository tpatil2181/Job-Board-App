package com.jobBordaApp.JobBoardApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.entity.UserResume;

public interface UserResumeReop extends JpaRepository<UserResume,Integer>{
	
	@Query(value="select ur from user_resume ur where ur.user_id=?1",nativeQuery = true)
	public UserResume findResumeByUserId(Integer userId);
	
	Optional<UserResume> findByUser(User user);
	
//	Optional<UserResume> findByUserId(Integer userId);
	
	Optional<UserResume> findByUser_UserId(Integer userId);
	
	

}
