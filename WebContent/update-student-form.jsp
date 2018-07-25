<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/css/style.css">
<link type="text/css" rel="stylesheet" href="/css/add-student-style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<div id="header">
<h2>List Add</h2>
</div>
</div>
<div id="container">
<h3>Update Student</h3>
<form action="StudentController" method="GET">
	<input type="hidden" name="command" value="UPDATE"/>
	<input type="hidden" name="StudentId" value="${TheStudent.id}"/>

	<table>
		<tbody>
			<tr>
				<td><label>First Name:</label></td>
				<td><input type="text" name="firstName" value="${TheStudent.firstName}"/> </td>
			</tr>
			<tr>
				<td><label>Last Name:</label></td>
				<td><input type="text" name="lastName" value= "${TheStudent.lastName}"/> </td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><input type="text" name="email" value= "${TheStudent.emailId}"/> </td>
			</tr>

			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Update"/> </td>
			</tr>
		</tbody>


	</table>



</form>
</div>
</body>
</html>