package com.irh.demo.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.irh.demo.restapi.entity.Employee;

@RepositoryRestResource(path="members")
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
