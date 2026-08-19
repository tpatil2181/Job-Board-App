package com.jobBordaApp.JobBoardApp.dto;

import java.time.LocalDate;
import java.util.List;

import com.jobBordaApp.JobBoardApp.enums.WorkMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchDTO {
	
	private String search;

    private String jobTitle;

    private String jobLocation;

    private String employerName;

    private List<WorkMode> workModes;

    private List<String> employmentTypes;

    private List<String> industryTypes;

    private Integer minSalary;

    private Integer maxSalary;

    private LocalDate datePosted;

    private List<ExperienceFilterDTO> experiences;

}