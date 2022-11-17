package com.md.producer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.md.producer.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Integer> {

}
