package com.jobBordaApp.JobBoardApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobBordaApp.JobBoardApp.entity.CandidateProject;

public interface CandidateProjectRepo extends JpaRepository<CandidateProject, Integer> {

}
