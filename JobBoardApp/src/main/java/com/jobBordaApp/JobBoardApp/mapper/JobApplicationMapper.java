package com.jobBordaApp.JobBoardApp.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.JobApplicationsDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
	
	
	@Mapping(source = "candidate.candidateId", target = "cndId")
	@Mapping(source = "candidate.firstName", target = "cndFname")
	@Mapping(source = "candidate.lastName", target = "cndLname")
	@Mapping(source = "job.jobId", target = "jobId")
	@Mapping(source = "employeer.employeerId", target = "employeerId")
	
	JobApplicationsDTO mapAppiedJobToJobApplicationDTO(ApplyJob appliedJob);


}
