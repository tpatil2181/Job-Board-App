package com.jobBordaApp.JobBoardApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.exception.ResourceNotFoundException;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepo candidateRepo;
	
	
	public Candidate getCandidateByCandidateId(@PathVariable Integer id) {
		Candidate candidate= candidateRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate not found with this ID"));
			return candidate;
	}
		
	

}
