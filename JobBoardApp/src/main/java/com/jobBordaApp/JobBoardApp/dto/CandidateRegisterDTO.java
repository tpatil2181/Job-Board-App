package com.jobBordaApp.JobBoardApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobNo;
}