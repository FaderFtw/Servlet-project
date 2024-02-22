<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Football Team Management System</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Welcome to Football Team Management System</h1>
    <p>This application allows you to manage football teams.</p>
    <p>Please login or register to get started.</p>
    <div>
        <a href="/Auth?action=viewLogin" class="btn">Login</a>
        <a href="/Auth?action=viewRegister" class="btn">Register</a>
    </div>
</div>
</body>
</html>
