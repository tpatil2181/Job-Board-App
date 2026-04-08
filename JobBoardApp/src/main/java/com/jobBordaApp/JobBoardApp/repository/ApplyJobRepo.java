package com.jobBordaApp.JobBoardApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.ApplyJob;

public interface ApplyJobRepo  extends JpaRepository<ApplyJob,Integer>{
	
	
	@Query(value="select * from apply_job where candidate_id=?1",nativeQuery = true)
	public List<ApplyJob> findAllApplicationsByCandidateId(Integer candidateId);
	
	
	@Query(value="select * from apply_job where job_id=?1",nativeQuery = true)
	public List<ApplyJob> findAllApplicantByJobId(Integer jobId);
	
	

	@Query(value="select * from apply_job  where candidate_id=?1 AND job_id=?2",nativeQuery = true)
	Optional<ApplyJob> findByUserIdAndJobId(Integer candidateId,Integer jobId);
	
	

}
