package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.CandidateExperienceDTO;
import com.jobBordaApp.JobBoardApp.entity.CandidateExperience;

@Mapper(componentModel = "spring")
public interface CandidateExperienceMapper {

    @Mapping(source = "candidate.candidateId", target = "candidateId")
    CandidateExperienceDTO map(CandidateExperience experience);
}