package com.jobBordaApp.JobBoardApp.dto;

import java.time.LocalDateTime;

import com.jobBordaApp.JobBoardApp.enums.JobApplicationStatus;

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
public class JobApplicationsDTO {
	
	    private Integer applyid;
	    
	    private Integer employeerId;  
	    
	    private Integer jobId;  
	    
	    private String cndId;
	    
	    private String cndFname;
	    
	    private String cndLname;
	    
		private LocalDateTime dateApplied ;	
		
		private JobApplicationStatus  status;

		

}
