package com.jobBordaApp.JobBoardApp.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jobBordaApp.JobBoardApp.dto.CandidateLanguageDTO;
import com.jobBordaApp.JobBoardApp.entity.Language;


@Mapper(componentModel = "spring")
public interface CandidateLanguageMapper {
	 @Mapping(source = "candidate.candidateId", target = "candidateId")
	  CandidateLanguageDTO map(Language Lang);
}
