package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.JobDTO;
import com.jobBordaApp.JobBoardApp.entity.Job;

@Mapper(componentModel = "spring")
public interface JobMapper {

	@Mapping(source = "employer.employerId",target = "employerId")

    @Mapping(source = "employer.companyName",target = "employerName")

    JobDTO mapJobToJobDTO(Job job);
}
