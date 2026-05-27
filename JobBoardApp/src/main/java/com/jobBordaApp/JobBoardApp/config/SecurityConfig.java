//package com.jobBordaApp.JobBoardApp.config;
//
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
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.jobBordaApp.JobBoardApp.entity.Candidate;
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
//	@Bean
//	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
//	  
//		return http
//	            .csrf(customizer -> customizer.disable())
//	            .authorizeHttpRequests(auth -> auth
//
//	                    // Public APIs
//	                    .requestMatchers(
//	                            "/register",
//	                            "/api/register",
//	                            "/candidate/register"
//	                    ).permitAll()
//
//	                    // Secure remaining APIs
//	                    .anyRequest().authenticated()
//	            )
//
//	            .httpBasic(Customizer.withDefaults())
//	            .sessionManagement(session ->
//	                    session.sessionCreationPolicy(
//	                            SessionCreationPolicy.STATELESS))
//	            .build();
//	}
//		
////		http.formLogin(Customizer.withDefaults());
//		//49.45 build it returns the object of security object filter chain 
//	
////My code 
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		 
//		 DaoAuthenticationProvider provider =new DaoAuthenticationProvider(userDetailsService);
//		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
////		 provider.setUserDetailsService(userDetailsService);
//		
//		return provider;
//	}
//
////chatgpt codde 
////	 @Bean
////	    public AuthenticationProvider authenticationProvider() {
////
////	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////
////	        provider.setUserDetailsService(userDetailsService);
////	        provider.setPasswordEncoder(passwordEncoder());
////
////	        return provider;
////	    }
//	
//	
////	@Bean 
////	public UserDetailsService userDetailsService() {
////		
////		UserDetails user1=User.withDefaultPasswordEncoder()
////				.username("Tushar")
////				.password("T@123")
////				.roles("USER")
////				.build();
////		
////		UserDetails user2=User.withDefaultPasswordEncoder()
////				.username("Patil")
////				.password("P@123")
////				.roles("ADMIN")
////				.build();
////		return  new InMemoryUserDetailsManager();
////		
////	}
//
//}
