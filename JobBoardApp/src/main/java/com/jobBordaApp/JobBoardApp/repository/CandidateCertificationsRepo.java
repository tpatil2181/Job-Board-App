package com.jobBordaApp.JobBoardApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;


public interface CandidateCertificationsRepo extends JpaRepository <CandidateCertification,Integer> {

}
