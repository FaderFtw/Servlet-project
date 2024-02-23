<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Teams</title>
    <script src="https://kit.fontawesome.com/20ee220576.js" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            height: 100vh;
        }
        .header{
            margin: 20px auto auto;
            max-width: 800px;
            display: flex;
            justify-content: space-between;
            align-items: center; /* Vertically center items */
        }
        .container {
            margin: 20px auto 20px auto;
            max-width: 800px;
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }

        .team-card {
            background-color: #f9f9f9;
            margin-bottom: 20px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            max-width: 250px;
            margin-right: 20px;
        }
        .team-card h2 {
            margin-bottom: 10px;
            font-size: 30px;
            color: #0056b3;
            font-family: Tahoma;
        }

        .team-card p {
            color: #666;
            margin-bottom: 10px;
            font-size: 16px;
        }

        .team-card .label{
            font-weight: bold;
            color: black;
        }

        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            font-size: 16px;
            font-weight: bold;
            margin-right: 10px; /* Add margin to separate buttons */
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .add-team-btn {
            background-color: #28a745;
        }

        .logout-btn {
            background-color: #dc3545; /* Red color for logout button */
        }

        .delete-btn {
            background-color: #dc3545; /* Red color for logout button */
        }

        .delete-btn:hover{
            background-color: #2b2d30;
            color: white
        }

        .toast {
            position: fixed;
            z-index: 10;
            bottom: 25px;
            right: 30px;
            border-radius: 12px;
            background: #fff;
            padding: 20px 35px 20px 25px;
            box-shadow: 0 6px 20px -5px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transform: translateX(calc(100% + 30px));
            transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.35);
        }

        .toast.active {
            transform: translateX(0%);
        }

        .toast .toast-content {
            display: flex;
            align-items: center;
        }

        .toast-content .success {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 35px;
            min-width: 35px;
            background-color: #129857;
            color: #fff;
            font-size: 20px;
            border-radius: 50%;
        }

        .toast-content .error {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 35px;
            min-width: 35px;
            background-color: red;
            color: #fff;
            font-size: 20px;
            border-radius: 50%;
        }

        .toast-content .message {
            display: flex;
            flex-direction: column;
            margin-left: 10px;
        }

        .message .text {
            font-size: 16px;
            font-weight: 400;
            color: #666666;
        }

        .message .text.text-1 {
            margin-bottom: 5px;
            font-weight: 600;
            color: #333;
        }

        .toast .progress {
            position: absolute;
            margin-bottom: 0;
            bottom: 0;
            left: 0;
            height: 3px;
            width: 100%;

        }

        .toast.active .progress {
            <c:choose>
                <c:when test="${not empty sessionScope.success}">
                    background-color: #28a745;
                </c:when>

                <c:when test="${not empty sessionScope.error}">
                    background-color: #dc3545;
                </c:when>
            </c:choose>
            animation: progress 5s linear forwards; /* Apply animation here */
        }

        @keyframes progress {
            100% {
                width: 0;
            }
        }

        .buttons{
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .addTitleBtn, .removeTitleBtn{
            border-radius: 25%;
            padding: 7px;
            width: 30px;
            height: 30px;
            font-size: 15px;
            text-align: center;
            color: white;
        }

        .addTitleBtn:hover, .removeTitleBtn:hover{
            background-color: #0056b3;
        }

        .addTitleBtn{
            background-color: #129857;
            margin-right: 20px;
        }

        .removeTitleBtn{
            background-color: red;
        }
    </style>
</head>
<body>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<div class="header">
    <div style="display: flex; flex-direction: column; text-align: center; align-self: center; padding-top: 20px;m">
        <h2 style="margin: 0">Welcome, <jsp:getProperty name="user" property="username"/></h2>
        <h1>Teams</h1>
    </div>
    <div style="display: flex;">

        <form action="/Teams" method="get">
            <button type="submit" name="action" value="viewTeamForm" class="btn add-team-btn">Add Team</button>
        </form>

        <form action="/Auth" method="post">
            <button type="submit" name="action" value="logout" class="btn logout-btn">Logout</button>
        </form>
    </div>
</div>
<jsp:useBean id="teams" class="java.util.ArrayList" scope="session"/>
<jsp:useBean id="team" class="beans.Team"/>

<div class="container">
    <c:forEach var="team" items="${teams}">
        <c:choose>
            <c:when test="${team.getNumberOfTitles() > 20}">
                <jsp:setProperty name="team" property="status" value="Excellent" />
            </c:when>
            <c:when test="${team.getNumberOfTitles() > 10}">
                <jsp:setProperty name="team" property="status" value="Good" />
            </c:when>
            <c:otherwise>
                <jsp:setProperty name="team" property="status" value="Average" />
            </c:otherwise>
        </c:choose>
        <div class="team-card">
            <h2><jsp:getProperty name="team" property="name"/></h2>
            <p><span class="label">Country:</span> <jsp:getProperty name="team" property="country"/></p>
            <p><span class="label">League:</span> <jsp:getProperty name="team" property="league"/></p>
            <p><span class="label">Number of Titles:</span> <jsp:getProperty name="team" property="numberOfTitles"/></p>
            <p><span class="label">Number of Players:</span> <jsp:getProperty name="team" property="numberOfPlayers"/></p>
            <p><span class="label">Stadium:</span> <jsp:getProperty name="team" property="stadium"/></p>
            <p><span class="label">Status:</span> <jsp:getProperty name="team" property="status"/></p>
            <form action="/Teams" method="post">
                <input type="hidden" name="teamId" value="${team.getId()}">
                <button type="submit" name="action" value="deleteTeam" class="btn delete-btn">Delete Team</button>
            </form>
        </div>
    </c:forEach>
</div>

<c:if test="${not empty sessionScope.success}">
    <% System.out.println("enter");%>
    <div class="toast active">
        <div class="toast-content">
            <i class="fas fa-check success"></i>
            <div class="message">
                <span class="text text-1">SUCCESS</span>
                <span class="text text-2">${sessionScope.success}</span>
            </div>
            <div class="progress active"></div>
        </div>
    </div>
    <% session.removeAttribute("success"); %>
</c:if>

<c:if test="${not empty sessionScope.error}">
    <div class="toast active">
        <div class="toast-content">
            <i class="fas fa-exclamation error"></i>
            <div class="message">
                <span class="text text-1">ERROR</span>
                <span class="text text-2">${sessionScope.error}</span>
            </div>
            <div class="progress active"></div>
        </div>
    </div>
    <% session.removeAttribute("error"); %>
</c:if>

</body>

<script>

    const toast = document.querySelector('.toast');
    toast.style.display = 'block';

    setTimeout(function() {
        toast.style.display = 'none';
    }, 5000);
</script>
</html>
