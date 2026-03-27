package com.jobBordaApp.JobBoardApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobBordaApp.JobBoardApp.entity.Employeer;


public interface EmployeerRepo  extends JpaRepository<Employeer,Integer> {

}
