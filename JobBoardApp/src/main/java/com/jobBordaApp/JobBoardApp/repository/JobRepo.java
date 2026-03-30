package com.jobBordaApp.JobBoardApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.Job;


public interface JobRepo  extends JpaRepository<Job,Integer> {

	
	@Query(value="select * from job u where employeerId=?1",nativeQuery = true)
	public List<Job> findAllJobsByComapanyId(int companyID); 
	
	@Query(value="select * from job u where userId=?1",nativeQuery = true)
	public List<Job> findAllapplicationOfPerticlarUser();
	
	
}
