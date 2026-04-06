//package com.jobBordaApp.JobBoardApp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.jobBordaApp.JobBoardApp.entity.User;
//import com.jobBordaApp.JobBoardApp.entity.UserPrincipal;
//import com.jobBordaApp.JobBoardApp.repository.UserRepo;
//
//public class MyUserDetailsService implements UserDetailsService{
//
//	
//
//	@Autowired
//	private UserRepo userRepo;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		
//		User user=userRepo.findByUsername(username);
//		
//		if(user==null) {
//			System.out.println();
//			throw new UsernameNotFoundException("User Not Found");
//		}
//		return new UserPrincipal(user);
//	}
//
//}
