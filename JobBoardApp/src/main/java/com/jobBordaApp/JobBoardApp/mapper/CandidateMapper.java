package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.jobBordaApp.JobBoardApp.dto.CandidateCertificationDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateEducationDTO;
import com.jobBordaApp.JobBoardApp.dto.CandidateExperienceDTO;
//import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;
import com.jobBordaApp.JobBoardApp.entity.CandidateExperience;


//Solve mapper implementation problem(Problem in pom.xm plugnin order of plugijn should be 1)Lombok 2)Lombok binding 3)MapStruct) and now implementation of mapper are generating and DTO getting in the postman
@Mapper(
	    componentModel = "spring",
	    uses = {
	        CandidateEducationMapper.class,
	        CandidateExperienceMapper.class,
	        CandidateCertificateMapper.class
	    }
	)
	public interface CandidateMapper {
	
		@Mapping(source = "resume.resumeId", target = "resumeId")

	    CandidateDTO mapCandidateToCandidateDTO(Candidate candidate);

	}