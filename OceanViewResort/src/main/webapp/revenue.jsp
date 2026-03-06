<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Total Revenue</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

<h2>Ocean View Resort Revenue</h2>

<%
Double revenue = (Double) request.getAttribute("revenue");
%>

<h3>Total Revenue: Rs. <%= revenue %></h3>

<br>

<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>