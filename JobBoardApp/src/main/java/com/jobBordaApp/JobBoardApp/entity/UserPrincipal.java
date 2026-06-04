package com.jobBordaApp.JobBoardApp.entity;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	
		private AppUser user;
	
	

	public UserPrincipal(AppUser user) {
			super();
			this.user = user;
		}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

	    return Collections.singleton(
	        new SimpleGrantedAuthority(
	            "ROLE_" + user.getRole().name()
	        )
	    );
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
//		return user.getUsername();  since i dont have username i am using email hear
		return user.getEmail();
	}

}
