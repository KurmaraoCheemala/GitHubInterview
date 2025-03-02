package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="ELIGIBILITY_DETAILS")
public class EligibilityDetails {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;
	private String name;
	private Long number;
	private String email;
	private Character gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	private String createdBy;
	private String updatedBy;
	
	
	

}
