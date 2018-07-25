package com.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class StudentDbUtil {
	private DataSource dataSource;
	public StudentDbUtil(DataSource theDataSource) {
		dataSource= theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students= new ArrayList<Student>();
		Connection myConn= null;
		Statement mystmt= null;
		ResultSet rS= null;
		try {
		//get a connection
		myConn= dataSource.getConnection();
		
		//sql statment
		String sql= "select * from student order by last_name";
		mystmt= myConn.createStatement();
		//execute the query
		rS= mystmt.executeQuery(sql);
		while(rS.next()) {
			//Retrieve all columns 
			int id= rS.getInt("id");
			String firstName= rS.getString("first_name");
			String lastName= rS.getString("last_name");
			String email= rS.getString("email");
			
			//create a new student object
			Student tempStudent= new Student(id,firstName,lastName,email);
			
			//insert the student object into Student list
			students.add(tempStudent);
			
		}
		return students;
		}
		finally {
			close(myConn,mystmt,rS);
		}
		
		
		//process the result
		
		
		
	}

	private void close(Connection myConn, Statement mystmt, ResultSet rS) {
		// TODO Auto-generated method stub
		try {
			if(rS!=null) {
				rS.close();
			}
			if(mystmt!=null) {
				mystmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
			
		}
		catch(Exception exc){
			exc.printStackTrace();
			
			
		}
	}

	public void addStudent(Student newStudent) throws Exception {
		Connection myConn= null;
		PreparedStatement mystmt= null;
		try {
			String sql= "Insert into student (first_name,last_name,email)values(?,?,?)";
			myConn= dataSource.getConnection();
			mystmt=myConn.prepareStatement(sql);
			mystmt.setString(1, newStudent.getFirstName());
			mystmt.setString(2, newStudent.getLastName());
			mystmt.setString(3, newStudent.getEmailId());
			mystmt.execute();
		}
		
		finally {
			close(myConn,mystmt,null);
		}
		
	}

	public Student getStudent(String studentId) throws Exception {
		int ID= Integer.parseInt(studentId);
		Connection myConn=null;
		PreparedStatement mystmt= null;
		ResultSet rs=null;
		try {
		//1. Connection
		 myConn= dataSource.getConnection();
		//2. prepare Statment
		String firstName="";
		String lastName="";
		String email="";
		
		String sql="select * from student where id=?";
		mystmt= myConn.prepareStatement(sql);
		mystmt.setString(1, studentId);
		
		rs= mystmt.executeQuery();
		while(rs.next()) {
			firstName= rs.getString("first_name");
			lastName= rs.getString("last_name");
			email= rs.getString("email");
		}
		Student tempStudent= new Student(Integer.parseInt(studentId),firstName,lastName,email);
		return tempStudent;
		}
		finally {
			close(myConn,mystmt,rs);
		}
		
	}

	public void updateStudent(Student updatedStudent) throws Exception{
		Connection myConn=null;
		PreparedStatement mystmt= null;
		String sql="";
		ResultSet rs=null;
		try {
		//open Connecton
		myConn=dataSource.getConnection();
		// SQL statment
		sql="Update student set first_name=?,last_name=?,email=? where id=?";
		
		// prepare Statement
		myConn.prepareStatement(sql);	
		mystmt.setString(1, updatedStudent.getFirstName());
		mystmt.setString(1, updatedStudent.getLastName());
		mystmt.setString(1, updatedStudent.getEmailId());
		mystmt.setInt(1, updatedStudent.getId());
		
		//execute
		mystmt.execute();
		}
		finally {
			close(myConn,mystmt,null);
		}
	}

}
