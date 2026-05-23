package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.CandidateEducationDTO;
import com.jobBordaApp.JobBoardApp.entity.CandidateEducation;

@Mapper(componentModel = "spring")
public interface CandidateEducationMapper {

    @Mapping(source = "candidate.candidateId", target = "candidateId")
    CandidateEducationDTO map(CandidateEducation education);
}