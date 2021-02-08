package com.ems.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ems.webapp.entity.Employee;
import com.ems.webapp.entity.Payroll;
import com.ems.webapp.util.HibernateUtil;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/add-employee-with-payroll")
public class AddEmployeeWithPayroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeWithPayroll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("add-employee-with-payroll.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String salary = request.getParameter("salary");
		String dept = request.getParameter("dept");
		
		String gross = request.getParameter("gross");
		String net = request.getParameter("net");
		String tax = request.getParameter("tax");
		
		// 1. build session factory
		SessionFactory factory = HibernateUtil.buildSessionFactory();
		
		// 2. open session 
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		// 3. create entity object
		Employee employee = new Employee(firstName, lastName, Double.parseDouble(salary), dept);
		Payroll payroll = new Payroll(Double.parseDouble(gross), Double.parseDouble(net), Double.parseDouble(tax));
		
		// add payroll to employee
		employee.setPayroll(payroll);
		
		// 4. save entity object
		session.save(employee);
		tx.commit();
		
		// 5. close session
		session.close();
		
		out.print("<h2> Employee Data is added successfuly </h2>");
	}


}
