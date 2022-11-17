package com.md.producer.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.md.producer.entity.Employee;
import com.md.producer.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String saveEmployee(@RequestBody Employee employee) {
		empRepo.save(employee);
		return "employee saved successfully";
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	@DeleteMapping
	public String deleteEmployee(@RequestParam Integer id) {
		empRepo.deleteById(id);
		return "Employee deleted Successfully";
	}

	@PutMapping
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
