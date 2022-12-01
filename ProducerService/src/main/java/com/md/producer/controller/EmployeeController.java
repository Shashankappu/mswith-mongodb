package com.md.producer.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.md.producer.entity.Employee;
import com.md.producer.repository.EmpRepo;


@RestController
@CacheConfig(cacheNames= {"Employees"})
public class EmployeeController {
	
	@Autowired
	private EmpRepo empRepo;

	@PostMapping("/saveEmp")
	public String saveEmployee(@RequestBody Iterable<Employee> employee) {
		empRepo.saveAll(employee);
		return "Employee Saved";
	}

	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> getAllEmployee() {
		return empRepo.findAll();
	}
	
	@GetMapping("/employee/{id}")
	@Cacheable
	@ResponseStatus(HttpStatus.OK)
	public Optional<Employee> getAllEmployee(@PathVariable Integer id) {
		return empRepo.findById(id);
	}

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		empRepo.deleteById(id);
		return "Employee deleted Successfully";
	}

	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> optEmp = empRepo.findById(employee.getId());
		if (optEmp.isPresent()) {
			Employee existEmployee = optEmp.get();
			if (Objects.nonNull(employee.getName())) {
				existEmployee.setName(employee.getName());
			}
			if (employee.getSalary() != 0.0) {
				existEmployee.setSalary(employee.getSalary());
			}

			return empRepo.save(existEmployee);
		}
		throw new RuntimeException("employee id not exist");

	}
}
