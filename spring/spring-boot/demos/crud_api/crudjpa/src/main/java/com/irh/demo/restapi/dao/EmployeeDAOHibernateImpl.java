package com.irh.demo.restapi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irh.demo.restapi.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	// Entity Manager is automatically created and injected by Spring Boot
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		// Get current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Create query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		
		// Execute query and get result list
		List<Employee> employees = query.getResultList();
		
		// Return the results
		return employees;
	}

	@Override
	public Employee findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee employee = currentSession.get(Employee.class, id);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		// if id=0 insert else update
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		@SuppressWarnings("rawtypes")
		Query query = currentSession.createQuery("DELETE FROM Employee WHERE ID=:employeeId");
		
		query.setParameter("employeeId", id);
		
		query.executeUpdate();
	}
}
