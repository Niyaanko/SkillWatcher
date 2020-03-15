<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String)request.getAttribute("ErrorMsg");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/LoginPage.css">
<title>Skill Watcher Login</title>
</head>
<body>

<div class="div-header">
	<img src="IMG/skill_watcher_logo.png">
	<h1>Skill Watcher</h1>
</div>

<div class="div-main">
	<h1>Want to visalize your skills ?</h1>
	<h2>あなたのスキルを可視化してみませんか ?</h2>
	<div class="line"></div>
	<h1>Login</h1>
	<form action="/SkillWatcher/LoginServlet" method="POST">
		<p>Mail Address<input type="text" name="mail_address"></p>
		<p> Password<input type="password" name="password"></p>
		<input type="submit" value="Login">
	</form>
	<% if(errorMsg != null){%>
	<p><%= errorMsg %></p>
	<% } %>
	<a href="/SkillWatcher/CreateAccountServlet">Create Account</a>
</div>

<div class="div-footer">
	<small>&copy;Hiromu Watanabe </small>
</div>

</body>
</html>