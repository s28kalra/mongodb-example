package com.example.mongodb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.example.mongodb.model.Address;
import com.example.mongodb.model.CityEmployee;
import com.example.mongodb.model.Employee;
import com.example.mongodb.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class MongoDbExampleApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MongoDbExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		init();
		var cities= new String[] {"narwana", "delhi"};
		var employees= employeeRepository.getByCity(cities);
		System.out.println(employees);
		
		MatchOperation matchOperation= Aggregation.match(new Criteria(Employee.getAddressStateName()).regex("HARYANA", "i"));
		GroupOperation groupOperation= Aggregation.group(Employee.getAddressCityName()).count().as("count");
		SortOperation sortOperation= Aggregation.sort(Sort.by(Direction.DESC, "count"));
//		first match, then group then sort
//		order is very important
		Aggregation aggregation= Aggregation.newAggregation(matchOperation,groupOperation, sortOperation);
		AggregationResults<CityEmployee> output= mongoTemplate.aggregate(aggregation, "employee", CityEmployee.class);
		List<CityEmployee> result= output.getMappedResults();
		System.out.println(result);
		
	}
	
	private void init() {
		var employees = Arrays.asList(
				new Employee(null, "Sagar1", "Kalra1", 10000.0, new Address("line1_1", "line2_1", "kaithal", "haryana", "136027")),
				new Employee(null, "Sagar2", "Kalra2", 20000.0, new Address("line1_2", "line2_2", "kaithal", "haryana", "136027")),
				new Employee(null, "Sagar3", "Kalra3", 30000.0, new Address("line1_3", "line2_3", "kaithal", "haryana", "136027")),
				new Employee(null, "Sagar4", "Kalra4", 40000.0, new Address("line1_4", "line2_4", "narwana", "haryana", "178962")),
				new Employee(null, "Sagar5", "Kalra5", 50000.0, new Address("line1_5", "line2_5", "kaithal", "haryana", "136027")),
				new Employee(null, "Sagar6", "Kalra6", 60000.0, new Address("line1_6", "line2_6", "kaithal", "haryana", "136027")),
				new Employee(null, "Sagar7", "Kalra7", 70000.0, new Address("line1_7", "line2_7", "delhi", "delhi", "110000")),
				new Employee(null, "Sagar8", "Kalra8", 80000.0, new Address("line1_8", "line2_8", "kaithal", "haryana", "136027")),
				new Employee(null, "Sagar9", "Kalra9", 90000.0, new Address("line1_9", "line2_9", "kaithal", "haryana", "136027"))
				);
		employeeRepository.saveAll(employees);
	}

}