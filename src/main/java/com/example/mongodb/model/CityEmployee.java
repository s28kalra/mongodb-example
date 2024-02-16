package com.example.mongodb.model;

import org.springframework.data.mongodb.core.mapping.MongoId;

public record CityEmployee(
		@MongoId
		String city,
		Long count
		) {

}
