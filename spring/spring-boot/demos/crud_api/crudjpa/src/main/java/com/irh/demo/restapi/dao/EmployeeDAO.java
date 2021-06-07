package com.irh.demo.restapi.dao;

import java.util.List;

import com.irh.demo.restapi.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
	
}
