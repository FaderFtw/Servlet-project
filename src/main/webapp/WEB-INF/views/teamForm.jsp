<!DOCTYPE html>
<html>
<head>
    <title>Add Team</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 500px;
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

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 40px);
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            font-size: 16px;
            font-weight: bold;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Add New Team</h1>
    <form action="${pageContext.request.contextPath}/AddTeamServlet" method="post">
        <input type="text" name="name" placeholder="Team Name" required><br>
        <input type="text" name="country" placeholder="Country" required><br>
        <input type="text" name="league" placeholder="League" required><br>
        <input type="number" name="numTitles" placeholder="Number of Titles" required><br>
        <input type="number" name="numPlayers" placeholder="Number of Players" required><br>
        <input type="text" name="stadium" placeholder="Stadium" required><br>
        <button type="submit" class="btn">Add Team</button>
    </form>
</div>

</body>
</html>
