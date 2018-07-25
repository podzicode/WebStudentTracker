package com.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command= request.getParameter("command");
		try {
			if (command == null) {

				command = "LIST";
			}
			switch (command) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				viewStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			default:
				listStudents(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		listStudents(request,response);
	}
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Read student data from form
		int studentId= Integer.parseInt(request.getParameter("StudentId"));
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String email=request.getParameter("email");
		Student updatedStudent= new Student(studentId,firstName,lastName,email);
		// Send the data to dbutil
		studentDbUtil.updateStudent(updatedStudent);
		//Update sucess then list students
		listStudents(request,response);
		
	}
	private void viewStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// 1. Read the Student ID
		String StudentId= request.getParameter("studentId");
		
		// 2. Get the Student from the DbUtil
		Student studentObject= studentDbUtil.getStudent(StudentId);
		//3.Add it to a request object
		request.setAttribute("TheStudent", studentObject);
		// Dispatch it 
		RequestDispatcher dispatcher= request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
		
	}
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String emailId=request.getParameter("email");
		Student newStudent= new Student(firstName,lastName,emailId);
		studentDbUtil.addStudent(newStudent);
		listStudents(request,response);
	}
	private void listStudents(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			// get students from db util class
			List<Student> students= studentDbUtil.getStudents();
			
			
			
			// add students to the request object
			request.setAttribute("STUDENTS_LIST",students);
			
			
			//send the students to jsp page through request dispatcher
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/list-students.jsp");
			dispatcher.forward(request,response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			studentDbUtil= new StudentDbUtil(dataSource);
			
		}
		catch(Exception exc){
			throw new ServletException(exc);
			
		}
	}

}
