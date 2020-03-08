<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String)request.getAttribute("ErrorMsg");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/SkillWatcher/LoginServlet" method="POST">
	Mail Address<input type="text" name="mail_address"><br>
	Password<input type="password" name="password"><br>
	<input type="submit" value="Login">
</form>
<% if(errorMsg != null){%>
<h2><%= errorMsg %></h2>
<% } %>
<a href="/SkillWatcher/CreateAccountServlet">Create Account</a>
</body>
</html>