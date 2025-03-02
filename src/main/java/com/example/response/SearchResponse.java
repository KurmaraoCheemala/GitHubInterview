package com.example.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SearchResponse {

	@Id
	@GeneratedValue
	private Integer siNo;
	private String name;
	private Long number;
	private String email;
	private Character gender;
	private Long ssn;
}
