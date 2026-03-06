<%
String logout = request.getParameter("logout");
if("1".equals(logout)){
%>
    <p style="color:green;font-weight:bold;">
        You have successfully exited the system.
    </p>
<%
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>

<h2>Login</h2>
<div class="container">
<form action="login" method="post">
Username: <input type="text" name="username"><br>
Password: <input type="password" name="password"><br>
<input type="submit" value="Login">
</form>
</div>

</body>
</html>