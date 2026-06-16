package com.jobBordaApp.JobBoardApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.jobBordaApp.JobBoardApp.dto.EmployeerDTO;
import com.jobBordaApp.JobBoardApp.entity.Employeer;



@Mapper(componentModel = "spring")
public interface EmployerMapper {
	
	
	  @Mapping(source = "user.email", target = "empEmail")
	  EmployeerDTO mapEmployerToEmployerDTO(Employeer employer);
	
	

}

