package com.irh.demo.restapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irh.demo.restapi.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	// Constructor injection
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	} //~

	@Override
	public List<Employee> findAll() {
		Query query = entityManager.createQuery("from Employee");
		
		@SuppressWarnings("unchecked")
		List<Employee> employees = query.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Employee savedEmployee = entityManager.merge(employee);
		
		employee.setId(savedEmployee.getId());
	}

	@Override
	public void deleteById(int id) {
		Query query = entityManager.createQuery("DELETE FROM Employee WHERE id=:employeeId");
		
		query.setParameter("employeeId", id);

		query.executeUpdate();
	}
}
