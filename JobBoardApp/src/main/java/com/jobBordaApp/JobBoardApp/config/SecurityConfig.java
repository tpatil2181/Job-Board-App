package com.jobBordaApp.JobBoardApp.config;


import java.util.List;


import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.jobBordaApp.JobBoardApp.entity.Candidate;

//Configuration File file 

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
	  
		
		return http
		        .cors(Customizer.withDefaults())
		        .csrf(csrf -> csrf.disable())
		        .authorizeHttpRequests(auth -> auth
	                    .requestMatchers(
	                    		"/cnd_register",
	                    		"/emp_register",
	                    		"/cnd_login",
	                    		"/emp_login",
	                    		"/Jobs",
	                    		"/jobsearch",
	            
	                            "/candidate/register",
	                            "/employer/register",
	                            "/candidate/login",
	                            "/employer/login"
//	                            "/employer/AllPostedJobs/**"
	                            
	                    ).permitAll()

	                    // Candidate APIs
//	                    .requestMatchers("/candidate/**")
	                    .requestMatchers("/Hireflow/candidate/**")
	                    .hasRole("CANDIDATE")

	                    // Employer APIs
	                    .requestMatchers("/employer/**")
	                    .hasRole("EMPLOYER")

	                    // Admin APIs
	                    .requestMatchers("/admin/**")
	                    .hasRole("ADMIN")

	                    // Everything else requires login
	                    .anyRequest().authenticated()
	
	            )

	            .httpBasic(Customizer.withDefaults())
	            .sessionManagement(session ->
	                    session.sessionCreationPolicy(
	                            SessionCreationPolicy.STATELESS))
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:4200"));

        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        configuration.setAllowedHeaders(
                List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

		
	@Bean
	public AuthenticationProvider authenticationProvider() {
		 
		 DaoAuthenticationProvider provider =new DaoAuthenticationProvider(userDetailsService);
		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));		
		return provider;
	}

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
		
		
	}

}
