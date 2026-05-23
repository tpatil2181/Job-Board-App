package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.CandidateCertificationDTO;
import com.jobBordaApp.JobBoardApp.entity.CandidateCertification;

@Mapper(componentModel = "spring")
public interface CandidateCertificateMapper {

    @Mapping(source = "candidate.candidateId", target = "candidateId")
    CandidateCertificationDTO map(CandidateCertification certification);
}