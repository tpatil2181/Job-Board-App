package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.jobBordaApp.JobBoardApp.dto.CandidateDTO;
import com.jobBordaApp.JobBoardApp.entity.Candidate;


@Component
@Mapper(componentModel = "spring")
public interface CandidateMapper {
	
	
//	@Mapping(source = )
	CandidateDTO mapCandidateToCandidateDTO(Candidate candidate);

}
