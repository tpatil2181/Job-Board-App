//package com.jobBordaApp.JobBoardApp.entity;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.jspecify.annotations.Nullable;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class UserPrincipal implements UserDetails {
//	
//		private Candidate candidate;
//	
//	
//
//	public UserPrincipal(Candidate candidate) {
//			super();
//			this.candidate = candidate;
//		}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return Collections.singleton(new SimpleGrantedAuthority("USER")) ;
//	}
//
//	@Override
//	public @Nullable String getPassword() {
//		// TODO Auto-generated method stub
//		return candidate.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
////		return user.getUsername();  since i dont have username i am using email hear
//		return candidate.getEmail();
//	}
//
//}
