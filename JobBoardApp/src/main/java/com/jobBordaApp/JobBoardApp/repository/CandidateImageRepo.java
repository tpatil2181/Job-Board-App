package com.jobBordaApp.JobBoardApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateImage;

//@Repository


public interface CandidateImageRepo extends JpaRepository<CandidateImage, Integer> {

    Optional<CandidateImage> findByCandidateCandidateId(Integer candidateId);
    
    Optional<CandidateImage> findByCandidate(Candidate candidate);

    Optional<CandidateImage> findByCandidate_CandidateId(Integer candidateId);
    
}