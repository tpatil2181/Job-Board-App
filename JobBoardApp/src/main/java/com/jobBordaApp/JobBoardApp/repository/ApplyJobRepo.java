package com.jobBordaApp.JobBoardApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.ApplyJob;

public interface ApplyJobRepo  extends JpaRepository<ApplyJob,Integer>{
	
	
	@Query(value="select * from apply_job where user_id=?1",nativeQuery = true)
	public List<ApplyJob> findByUserId(Integer UserId);

}
