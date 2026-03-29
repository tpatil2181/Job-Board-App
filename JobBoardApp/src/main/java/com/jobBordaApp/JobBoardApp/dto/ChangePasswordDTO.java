package com.jobBordaApp.JobBoardApp.dto;

public class ChangePasswordDTO {
	private String currentPass;
	private String newPass;
	
	
	public ChangePasswordDTO() {
		// TODO Auto-generated constructor stub
	}


	public ChangePasswordDTO(String currentPass, String newPass) {
		super();
		this.currentPass = currentPass;
		this.newPass = newPass;
	}


	public String getCurrentPass() {
		return currentPass;
	}


	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}


	public String getNewPass() {
		return newPass;
	}


	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	
	

}
