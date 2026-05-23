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
public class AppliedJobDTO {
	
	String JobTitle;
	String Comapny;
	String WorkMode;
	String AppliedOn;
	String Status;

}
