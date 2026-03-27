package com.jobBordaApp.JobBoardApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.JobRepo;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;

@RestController
//@RequestMapping("/jobBoardApp/employeer")
public class EmployeerController {
	
	@Autowired
	UserRepo userRepo;   //Reporisotory variable

	
	@Autowired
	EmployeerRepo employeerRepo;
	
	
	@GetMapping("/test")
	public String testEmoployeer() {
		return "Employeer run successfully";
	}
	
	
//==============Comapny  releted  CURD operations by company=================
	
	
	@PostMapping("/registerCompany")
	public String registerCompany(@RequestBody Employeer company) {
		
		employeerRepo.save(company);   //Check if comapny is already exist or not
		return "Company register successfully";
		
	}
	
	@PostMapping("/login")
	public String companyLogin() {
		
		
		return "login DSuccessfully";
	}
	
	
	@GetMapping("/company/{employeerId}")
	public Employeer getCompanyProfile(@PathVariable int companyId) {
		Employeer company= employeerRepo.findById(companyId).orElseThrow();
		return company;
		
	}
	
	@PutMapping("/company/{employeerId}")
	public String updateCompanyProfile(@PathVariable int companyId, @RequestBody Employeer updatedComapny) {
		Employeer existingCompany= employeerRepo.findById(companyId).orElseThrow();
		
		
		
		existingCompany.setEmployeerName(updatedComapny.getEmployeerName());
		existingCompany.setWebsite(updatedComapny.getWebsite());
		existingCompany.setContact(updatedComapny.getContact());
		existingCompany.setEmail(updatedComapny.getEmail());
		existingCompany.setJoblist(updatedComapny.getJoblist());
		//Comment job list beacouse it should not be part of Employeer entity
		    
		employeerRepo.save(existingCompany);
		
		return "Company profile updated";
	}
	
	@DeleteMapping("/company/{id}")
	public String deleteCompany(@PathVariable int companyId) {
		Employeer company= employeerRepo.findById(companyId).orElseThrow();
		employeerRepo.delete(company);
		return "Companny deleted";
	}
	
	
//==============Other Services=================	
	
	@GetMapping("/allCompanies ")
	public List<Employeer> getAllCompanies() {
		List<Employeer> companyies=employeerRepo.findAll();
		return companyies;
		
	}
	
	
	
	
	
//==============Job releted operations by company=================
	
	
	
	
	
	
	
	

}
