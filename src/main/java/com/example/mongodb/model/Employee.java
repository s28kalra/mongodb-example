package com.example.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Double salary;
	private Address address;

	public static String getIdName() {
		return "id";
	}

	public static String getFirstNameName() {
		return "firstName";
	}

	public static String getLastNameName() {
		return "lastName";
	}

	public static String getSalaryName() {
		return "salary";
	}
	
	public static String getAddressStateName() {
		return "address.state";
	}
	
	public static String getAddressCityName() {
		return "address.city";
	}
	
	public static String getCollectionName() {
		return "employee";
	}
	

}
