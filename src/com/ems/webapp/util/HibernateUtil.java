package com.ems.webapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ems.webapp.entity.Employee;
import com.ems.webapp.entity.Payroll;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	public static SessionFactory buildSessionFactory() {
		
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Payroll.class)
				.buildSessionFactory();		
		return factory;
	}
}
