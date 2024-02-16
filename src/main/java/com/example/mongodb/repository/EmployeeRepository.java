package com.example.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongodb.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
	
	@Query(value= "{'address.city': {$in: ?0}}")
	List<Employee> getByCity(String[] cities);

}
