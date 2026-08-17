package com.jobBordaApp.JobBoardApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.EmployerLogo;

//public interface EmployerLogoRepo {
//
//}

public interface EmployerLogoRepo extends JpaRepository<EmployerLogo, Integer> {

		Optional<EmployerLogo> findByEmployer(Employeer employer);
		
		Optional<EmployerLogo> findByEmployer_EmployeerId(Integer employerId);
}