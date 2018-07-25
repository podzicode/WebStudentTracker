<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/css/style.css">
<link type="text/css" rel="stylesheet" href="/css/add-student-style.css">
</head>
<body>

<div id="wrapper">


<div id="header">
<h2>List Add</h2>
</div>
</div>
<div id="container">
<h3>Add Student</h3>
<form action="StudentController">
<input type="hidden" name="command" value="ADD"/>
<table>
<tbody>
<tr>
<td><label>First Name:</label></td>
<td><input type="text" name="firstName"/> </td>
</tr>
<tr>
<td><label>Last Naem:</label></td>
<td><input type="text" name="lastName"/> </td>
</tr>
<tr>
<td><label>Email:</label></td>
<td><input type="text" name="email"/> </td>
</tr>

<tr>
<td><label></label></td>
<td><input type="submit" value="Save"/> </td>
</tr>
</tbody>


</table>



</form>


</div>




</body>
</html>