//package com.jobBordaApp.JobBoardApp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.jobBordaApp.JobBoardApp.entity.Candidate;
//import com.jobBordaApp.JobBoardApp.entity.UserPrincipal;
//import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
//
//
//@Service
//public class MyUserDetailsService implements UserDetailsService{
//
//	
//
//	@Autowired
//	private CandidateRepo candidateRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		
//		Candidate candidate=candidateRepo.findByEmail(username);
//		
//		if(candidate==null) {
//			System.out.println();
//			throw new UsernameNotFoundException("User Not Found");
//		}
//		return new UserPrincipal(candidate);
//	}
//
//}
