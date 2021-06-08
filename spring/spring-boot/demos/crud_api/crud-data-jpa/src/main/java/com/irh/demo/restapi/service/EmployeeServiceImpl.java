package com.irh.demo.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irh.demo.restapi.dao.EmployeeRepository;
import com.irh.demo.restapi.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// Constructor injection
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> result = this.employeeRepository.findById(id);

		Employee employee = null;
		
		if (result.isPresent()) {
			employee = result.get();
		}
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		 this.employeeRepository.save(employee);
	}

	@Override
	public void deleteById(int id) {
		this.employeeRepository.deleteById(id);
	}

}
