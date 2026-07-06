package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.AppliedJobDTO;
import com.jobBordaApp.JobBoardApp.dto.EmployeerDTO;
import com.jobBordaApp.JobBoardApp.dto.JobDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.Job;

@Mapper(componentModel = "spring")
public interface AppliedJobMapper {
	
	@Mapping(source = "job.jobTitle",target = "JobTitle")
    @Mapping(source = "employeer.employeerName",target = "Comapny")
	@Mapping(source = "job.workMode",target = "WorkMode")
//	@Mapping(source = "job.Status",target = "employerName")
//	@Mapping(source = "employer.employeerName",target = "employerName")
//	
//	@Mapping(source = "employer.employeerName",target = "employerName")

//	EmployeerDTO mapEmployerToEmployerDTO(Employeer employer);
	
//    JobDTO mapJobToJobDTO(Job job);


	AppliedJobDTO mapAppiedJobToApplyjobDTO(ApplyJob appliedJob);

}
