package com.irh.demo.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irh.demo.restapi.entity.Employee;
import com.irh.demo.restapi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	// Inject employee DAO
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return this.employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee employee = this.employeeService.findById(employeeId);
				
		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		// Force a save instead of update
		employee.setId(0);
		
		this.employeeService.save(employee);
		
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		this.employeeService.save(employee);
		
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = this.employeeService.findById(employeeId);
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		this.employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}
}
