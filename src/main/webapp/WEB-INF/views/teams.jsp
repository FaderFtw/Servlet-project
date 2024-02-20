<!DOCTYPE html>
<html>
<head>
    <title>Teams</title>
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
            margin: 20px auto auto;
            max-width: 800px;
            display: flex;
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
            padding: 20px;
            border-radius: 5px;
            margin-right: 20px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .team-card h2 {
            margin-bottom: 10px;
            font-size: 20px;
        }

        .team-card p {
            color: #666;
            margin-bottom: 10px;
            font-size: 16px;
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

        .add-team-btn {
            background-color: #28a745;
        }

        .logout-btn {
            background-color: #dc3545; /* Red color for logout button */
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Teams</h1>
    <div style="display: flex;">
        <form action="LogoutServlet" method="post">
            <button class="btn add-team-btn">Add Team</button>
            <button type="submit" class="btn logout-btn">Logout</button>
        </form>
    </div>
</div>

<div class="container">
    <!-- Team Cards -->
    <div class="team-card">
        <h2>Team 1</h2>
        <p>Country: Country 1</p>
        <!-- Add other team details -->

        <!-- View Details Button -->
        <button class="btn">View Details</button>
    </div>

    <div class="team-card">
        <h2>Team 2</h2>
        <p>Country: Country 2</p>
        <button class="btn">View Details</button>
    </div>
</div>

</body>
</html>
