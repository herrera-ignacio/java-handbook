package com.irh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.irh.hibernate.entity.Student;

public class ReadStudentDemo {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			Student tempStudent = CreateStudentDemo.createNewStudent();
			
			System.out.println("[LOG] Reading student with id: " + tempStudent.getId());
			
			Student student = session.get(Student.class, tempStudent.getId());
			
			System.out.println("[LOG] Success: " + student);
			
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}
}
