package com.jobBordaApp.JobBoardApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobBoardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardAppApplication.class, args);
	}

}

// use .\mvnw.cmd clean package if following problem occures
//If there is any problem with Mapper generation go the project folder open cd and hit following command  .\mvnw.cmd clean compile If new project .\mvnw.cmd clean Install and .\mvnw.cmd clean compile
 