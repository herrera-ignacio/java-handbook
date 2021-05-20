package com.irh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.irh.hibernate.entity.Student;

public class UpdateStudentDemo {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// Single update example
			int studentId = 1;
			
			session = factory.getCurrentSession();

			session.beginTransaction();
			
			System.out.println("[LOG] Read student by id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("[LOG] Update student");
			
			myStudent.setFirstName("Roman");
			
			session.getTransaction().commit();
			
			// Bulk update example
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("[LOG] Update email for all students");
			
			session.createQuery("update Student set email='foo@gmail.com'")
				.executeUpdate();
		
			session.getTransaction().commit();
			
			System.out.println("[LOG] Done!");
		}
		finally {
			factory.close();
		}
	}
}
