package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.CandidateProjectDTO;
import com.jobBordaApp.JobBoardApp.entity.CandidateProject;

@Mapper(componentModel = "spring")
public interface CandidateProjectMapper {
	
    @Mapping(source = "candidate.candidateId", target = "candidateId")
    CandidateProjectDTO map(CandidateProject education);

}
