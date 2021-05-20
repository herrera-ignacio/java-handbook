package com.irh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.irh.hibernate.entity.Student;


public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 5;
			
			session.beginTransaction();
			
			System.out.println("[LOG] Deleting student by id: " + studentId);
			
			// Option 1
//			Student myStudent = session.get(Student.class, studentId);
//			session.delete(myStudent);
			
			// Option 2
			session.createQuery("delete from Student where id=" + studentId).executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("[LOG] Done!");
		}
		finally {
			factory.close();
		}
	}
}
