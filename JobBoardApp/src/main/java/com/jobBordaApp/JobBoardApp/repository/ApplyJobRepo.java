package com.jobBordaApp.JobBoardApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.ApplyJob;

import jakarta.transaction.Transactional;

public interface ApplyJobRepo  extends JpaRepository<ApplyJob,Integer>{
	
	
	@Query(value="select * from apply_job where candidate_id=?1",nativeQuery = true)
	public List<ApplyJob> findAllApplicationsByCandidateId(Integer candidateId);
	
	
	@Query(value="select * from apply_job where job_id=?1",nativeQuery = true)
	public List<ApplyJob> findAllApplicantByJobId(Integer jobId);
	
	

	@Query(value="select * from apply_job  where candidate_id=?1 AND job_id=?2",nativeQuery = true)
	Optional<ApplyJob> findByUserIdAndJobId(Integer candidateId,Integer jobId);
	
	
	@Query(value="select * from apply_job  where candidate_id=?1 AND job_id=?2 AND employeer_id=?3",nativeQuery = true)
	Optional<ApplyJob> getJobApplicationByEmpJobCndId(Integer candidateId,Integer jobId, Integer EmployeerId);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM apply_job WHERE candidate_id=?1 AND job_id=?2",nativeQuery = true)
	int deleteByCandidateIdAndJobId(Integer candidateId,Integer jobId);
	
	
	

}
