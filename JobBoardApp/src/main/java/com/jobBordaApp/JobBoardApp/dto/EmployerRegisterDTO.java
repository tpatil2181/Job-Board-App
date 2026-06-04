package com.jobBordaApp.JobBoardApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerRegisterDTO {

    private String employerName;
    private String website;
    private String email;
    private String password;
    private Long contact;
}