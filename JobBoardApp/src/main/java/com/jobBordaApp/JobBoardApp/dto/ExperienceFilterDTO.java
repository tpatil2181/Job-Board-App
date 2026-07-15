package com.jobBordaApp.JobBoardApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceFilterDTO {

    private Integer minExperience;

    private Integer maxExperience;

}