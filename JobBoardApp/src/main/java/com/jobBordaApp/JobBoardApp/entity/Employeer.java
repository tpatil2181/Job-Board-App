package com.jobBordaApp.JobBoardApp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name="Employeer")
public class Employeer {
	
	@Id()
	@GeneratedValue(strategy= GenerationType.AUTO)
	Integer employeerId;
	@Column(name="employeer_name")
	String employeerName;
	@Column(name="website")
	String website;
	@Column(name="contact")
	Long contact;
	@Column(name="joblist")
	String joblist;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private AppUser user;

//	{
//		  "employeerName":"Vikram",
//		  "website":"www.google.com",
//		  "email":"vikram.singh@gmail.com",
//		  "password":3333,
//		  "contact":9090909090,
//		  "joblist":"L"
//	}	
	

}
