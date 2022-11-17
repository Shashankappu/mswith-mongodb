package com.md.Client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.Client.model.Employee;
import com.md.Client.service.EmployeeService;

@RestController
@RequestMapping("/employee-client")
public class EmpResourceController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping
	public String saveEmployee(@RequestBody Employee employee) {
		return empService.saveEmp(employee);
	}
	
	@GetMapping
	public List<Employee> getAllEmployee(){
		return empService.getAllEmployee();
	}
}
