<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>
<h1>Create Account</h1>
<form action="/SkillWatcher/CreateAccountServlet" method="POST">
	Mail Address<input type="text" name="create_mail_address"><br>
	Password<input type="password" name="create_password"><img src="IMG/eye.png"><br>
	<input type="submit" value="Regist">
</form>
</body>
</html>