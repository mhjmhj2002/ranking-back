package com.mhj.ranking.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone;

}
