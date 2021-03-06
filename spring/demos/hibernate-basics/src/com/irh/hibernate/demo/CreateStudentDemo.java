package com.irh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.irh.hibernate.entity.Student;

public class CreateStudentDemo {
	
	public static Student createNewStudent() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		Student tempStudent = new Student("Nacho", "Herrera", "ignacioromanherrera@gmail.com");

		try {
			System.out.println("[LOG] Creating new student object...");

			session.beginTransaction();

			System.out.println("[LOG] Saving the student...");

			session.save(tempStudent);

			session.getTransaction().commit();

			System.out.println("[LOG] Done!");
		}
		finally {
			factory.close();
		}
		
		return tempStudent; 
	}

	public static void main(String[] args) {
			createNewStudent();
			System.out.println("[LOG] Done!");
	}

}
