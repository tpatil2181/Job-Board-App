package com.jobBordaApp.JobBoardApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateResume;

public interface CandidateResumeReop extends JpaRepository<CandidateResume,Integer>{
	
	@Query(value="select cr from candidate_resume cr where cr.candidate_id=?1",nativeQuery = true)
	public CandidateResume findResumeByCandidateId(Integer candidateId);
	
	Optional<CandidateResume> findByCandidate(Candidate candidate);
	
//	Optional<UserResume> findByUserId(Integer userId);
	
	Optional<CandidateResume> findByCandidate_CandidateId(Integer candidateId);
	
	

}
