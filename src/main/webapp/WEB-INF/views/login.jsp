<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Football Team Management System</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <form action="/Auth" method="post">
        <input type="text" name="username" placeholder="Username" required><br><br>
        <input type="password" name="password" placeholder="Password" required><br><br>
        <button type="submit" name="action" value="login" class="btn">Login</button>
    </form>
    <p>Don't have an account? <a href="/Auth?action=viewRegister">Register</a></p>
</div>
</body>
</html>