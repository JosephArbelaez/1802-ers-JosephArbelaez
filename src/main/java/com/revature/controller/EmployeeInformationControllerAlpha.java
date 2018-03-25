package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Employee;
import com.revature.service.EmployeeServiceAlpha;

public class EmployeeInformationControllerAlpha implements EmployeeInformationController {

	private static EmployeeInformationController employeeInformationController = new EmployeeInformationControllerAlpha();

	private EmployeeInformationControllerAlpha() {}

	public static EmployeeInformationController getInstance(){
		return employeeInformationController;
	}

	@Override
	public Object registerEmployee(HttpServletRequest request) {
		if (request.getMethod().equals("GET")){
			return "login.html";
		}
		Employee employee = new Employee(0, request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("userName"),
				request.getParameter("password"),
				request.getParameter("email"));
		if (EmployeeServiceAlpha.getInstance().createEmployee(employee)) {
			return new ClientMessage("REGISTRATION SUCCESSUL");
		} else {
			return new ClientMessage("SOMETHING WENT WRONG");
		}
	}

	@Override
	public Object updateEmployee(HttpServletRequest request) {
		if (request.getMethod().equals("GET")){
			return "login.html";
		}
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");
		
		if (loggedEmployee == null) {
			return "login.html";
		}
		
		Employee employee = new Employee (
				loggedEmployee.getId(),
				request.getParameter("firstname"),
				request.getParameter("lastname"),
				request.getParameter("username"),
				null,
				request.getParameter("email"),
				loggedEmployee.getEmployeeRole());
		
		if (EmployeeServiceAlpha.getInstance().updateEmployeeInformation(employee)) {
			return new ClientMessage("Update Successful!");
		} else {
			return new ClientMessage("Update Failed...");
		}
	}

	@Override
	public Object viewEmployeeInformation(HttpServletRequest request) {
		if (request.getMethod().equals("GET")){
			return "login.html";
		} 
		
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");
		if(loggedEmployee == null) {
			return"login.html";
		} else {
			return EmployeeServiceAlpha.getInstance().getEmployeeInformation(loggedEmployee);
		}
	}

	@Override
	public Object viewAllEmployees(HttpServletRequest request) {
		if (request.getMethod().equals("GET")){
			return "register.html";
		}
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");

		/* If customer is not logged in */
		if(loggedEmployee == null) {
			return "login.html";
		} else if (loggedEmployee.getEmployeeRole().getId() == 2) {
			return EmployeeServiceAlpha.getInstance().getAllEmployeesInformation();
		} else {
			return "404.html";
		}
	}

	@Override
	public Object usernameExists(HttpServletRequest request) {
		if (request.getMethod().equals("GET")){
			return "register.html";
		}

		boolean exists = EmployeeServiceAlpha.getInstance().isUsernameTaken(new Employee(0, request.getParameter("username"), null, null, null, null));

		if(exists) {
			return new ClientMessage("Username has already been taken");
		} else {
			return new ClientMessage("Username Available");
		}

	}
}