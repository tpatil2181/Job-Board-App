//package com.jobBordaApp.JobBoardApp.config;
//
//import java.net.Authenticator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
//import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.jobBordaApp.JobBoardApp.entity.User;
//
////Configuration File file 
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig { 
//	
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	
////	@Bean
//	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) {
//		
//		
//		
//		return http
//				.csrf(customizer-> customizer.disable())
//				.authorizeHttpRequests(request->request.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
//				.sessionManagement(session->
//						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.build();
//
//		
////		http.formLogin(Customizer.withDefaults());
//		//49.45 build it returns the object of security object filter chain 
//	}
//	
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		 
//		 DaoAuthenticationProvider provider =new DaoAuthenticationProvider(null);
//		 provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		 provider.setUserDetailsService(userDetailsService);
//		
//		return provider;
//	}
//	
//	
////	@Bean 
////	public UserDetailsService userDetailsService() {
////		
////		UserDetails user1=User.withDe
////		
////		return  new InMemoryUserDetailsManager();
////		
////	}
//
//}
