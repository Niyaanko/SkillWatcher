<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String)request.getAttribute("ErrorMsg");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="">
<title>Create Account</title>
</head>
<body>
<h1>Create Account</h1>
<form action="/SkillWatcher/CreateAccountServlet" method="POST">
	Mail Address<input type="text" name="create_mail_address"><br>
	Password<input type="password" name="create_password"><img src="IMG/eye.png"><br>
	<input type="submit" value="Regist">
</form>
<% if(errorMsg != null){%>
<h2><%= errorMsg %></h2>
<% } %>
<a href="/SkillWatcher/LoginServlet">Login</a>
</body>
</html>