package com.jobBordaApp.JobBoardApp.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ChangePasswordDTO {
	private Integer Id;
	private String currentPass;
	private String newPass;
	
	}
