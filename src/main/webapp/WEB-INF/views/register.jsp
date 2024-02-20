<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Football Team Management System</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<div class="container">
    <h1>Register</h1>
    <form action="/RegisterServlet" method="post">
        <input type="text" name="username" placeholder="Username" required><br><br>
        <input type="email" name="email" placeholder="Email" required><br><br>
        <input type="password" name="password" placeholder="Password" required><br><br>
        <button type="submit" class="btn">Register</button>
    </form>
    <p>Already have an account? <a href="/loginPage">Login</a></p>
</div>
</body>
</html>