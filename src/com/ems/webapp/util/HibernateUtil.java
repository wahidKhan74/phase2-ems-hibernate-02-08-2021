package com.ems.webapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ems.webapp.entity.Employee;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	public static SessionFactory buildSessionFactory() {
		
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();		
		return factory;
	}
}
