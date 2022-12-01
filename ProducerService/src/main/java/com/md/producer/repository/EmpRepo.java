package com.md.producer.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.md.producer.entity.Employee;

@Repository
public interface EmpRepo extends ElasticsearchRepository<Employee,Integer> {
	
}

