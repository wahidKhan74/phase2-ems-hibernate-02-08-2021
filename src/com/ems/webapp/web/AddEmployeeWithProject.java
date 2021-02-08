package com.ems.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ems.webapp.entity.Employee;
import com.ems.webapp.entity.Project;
import com.ems.webapp.util.HibernateUtil;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/add-employee-with-project")
public class AddEmployeeWithProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeWithProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("add-employee-with-project.html");
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
		
		
		
		String P1Name = request.getParameter("project1-name");
		String P1No = request.getParameter("project1-no");
		String P2Name = request.getParameter("project2-name");
		String P2No = request.getParameter("project2-no");
		
		// 1. build session factory
		SessionFactory factory = HibernateUtil.buildSessionFactory();
		
		// 2. open session 
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		// 3. create entity object
		Employee employee = new Employee(firstName, lastName, Double.parseDouble(salary), dept);
		
		List<Project> projects = new ArrayList<>();
		Project p1 = new Project(P1Name, P1No);
		Project p2 = new Project(P2Name, P2No);
		projects.add(p1);
		projects.add(p2);
		
		
		// set projects into employee
		employee.setProjects(projects);
		
		// 4. save entity object
		session.save(employee);
		tx.commit();
		
		// 5. close session
		session.close();
		
		out.print("<h2> Employee Data is added successfuly </h2>");
	}


}
