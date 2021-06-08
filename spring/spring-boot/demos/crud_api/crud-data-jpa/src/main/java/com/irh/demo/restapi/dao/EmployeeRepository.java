package com.irh.demo.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irh.demo.restapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	/*
	 * CRUD Methods already implemented:
	 * findAll()
	 * findById()
	 * save()
	 * deleteById()
	 * ...
	 */
}
