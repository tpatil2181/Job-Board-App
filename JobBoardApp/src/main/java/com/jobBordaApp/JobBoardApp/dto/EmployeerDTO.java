package com.jobBordaApp.JobBoardApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeerDTO {
	
	Integer employeerId;
	String employeerName;
	String empEmail;
	String website;
	Long contact;
	

}
