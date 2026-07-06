package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.JobDTO;
import com.jobBordaApp.JobBoardApp.dto.PostedJobDTO;
import com.jobBordaApp.JobBoardApp.entity.Job;



@Mapper(componentModel = "spring")
public interface JobMapper {

	@Mapping(source = "employer.employeerId",target = "employerId")

    @Mapping(source = "employer.employeerName",target = "employerName")

    JobDTO mapJobToJobDTO(Job job);
	
	PostedJobDTO JobTopostedJobDTO(Job job);
}
