package com.jobBordaApp.JobBoardApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.jobBordaApp.JobBoardApp.entity.Language;

public interface LanguageRepo extends JpaRepository<Language,Integer> {
	

}
