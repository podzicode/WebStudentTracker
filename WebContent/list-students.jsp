<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Student Tracker APP</title>
</head>
<body>

<div id="wrapper">
	<div id="header">
 		<h2>List of Students</h2>
 		</div>
 		</div>
 		
 		<div id="container">
 		<div id="content">
 		<input type="button" value="Add Student" 
 		onclick="window.location.href='add-student.jsp';return false;" 
 		class="add-student-button"></input>
 		<table>
 		<tr>
 		<th>First Name</th>
 		<th>Last Name</th>
 		<th>Email</th>
 		<th>Action</th>
 		</tr>
 		
 		<c:forEach var="tempStudent" items="${STUDENTS_LIST }">
 		<c:url var="tempLink" value="StudentController">
 		<c:param name="command" value="LOAD"/>
 		<c:param name="studentId" value="${tempStudent.id }"/>
 		</c:url>
 		
 		<tr>
 		<td>${tempStudent.firstName}</td>
 		<td>${tempStudent.lastName}</td>
 		<td>${tempStudent.emailId}</td>
 		<td><a href="${ tempLink}">Update</a></td>
 		</tr>
 		</c:forEach>
 		
 		
 		</table>
 		
 		</div>
 		
 		
 		</div>
</body>
</html>