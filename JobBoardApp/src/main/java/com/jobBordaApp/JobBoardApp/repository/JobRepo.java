package com.jobBordaApp.JobBoardApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.Job;


public interface JobRepo  extends JpaRepository<Job,Integer>, JpaSpecificationExecutor<Job> {

	
	@Query(value="select * from job u where employeerId=?1",nativeQuery = true)
	public List<Job> findAllJobsByComapanyId(int companyID); 
	
	@Query(value="select * from job u where candidate_Id=?1",nativeQuery = true)
	public List<Job> findAllapplicationOfPerticlarCandidate();
	
	
	
	public Page<Job>findByJobTitle(String jobTitle,Pageable pageable);
	
	
//	public List<Job> fetchAllJobs(Pageable pageable){
//		return 
//	}
	
	
}
