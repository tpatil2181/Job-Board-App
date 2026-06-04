package com.jobBordaApp.JobBoardApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobBordaApp.JobBoardApp.entity.AppUser;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.UserPrincipal;
import com.jobBordaApp.JobBoardApp.repository.AppUserRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;


@Service
public class MyUserDetailsService implements UserDetailsService{

	

	@Autowired
	private AppUserRepo apUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		Candidate candidate=candidateRepo.findByEmail(username);
		AppUser usr=apUserRepo.findByEmail(username);
		
		if(usr==null) {
			System.out.println();
			throw new UsernameNotFoundException("User Not Found");
		}
		return new UserPrincipal(usr);
	}

}
