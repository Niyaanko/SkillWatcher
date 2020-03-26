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
	<h1 class="catch-copy">Want to visalize your skills ?</h1>
	<h2>あなたのスキルを可視化してみませんか ?</h2>
	<div class="div-line"></div>
	<h1 class="login">Login</h1>
	<form action="/SkillWatcher/LoginServlet" method="POST">
		<p class="mail">Mail Address<input class="textbox" type="text" name="mail_address"></p>
		<p class="pass"> Password<input class="passbox" type="password" name="password"></p>
		<input class="login-button" type="submit" value="Login">
	</form>
	<% if(errorMsg != null){%>
	<p><%= errorMsg %></p>
	<% } %>
	<a class="create-link" href="/SkillWatcher/CreateAccountServlet">Create Account</a>
</div>

<div class="div-footer">
	<small>&copy;Hiromu Watanabe </small>
</div>

</body>
</html>