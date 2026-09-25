<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.finance.model.Analytics" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Financial Analytics</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            margin: 0;
            background: #f4f6f9;
        }

        .navbar {
            background: #1f2937;
            color: white;
            padding: 18px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h2 {
            margin: 0;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
        }

        .container {
            width: 90%;
            max-width: 1000px;
            margin: 40px auto;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 14px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }

        .card h3 {
            color: #666;
            margin-bottom: 12px;
        }

        .amount {
            font-size: 28px;
            font-weight: bold;
        }

        .income {
            color: #16a34a;
        }

        .expense {
            color: #dc2626;
        }

        .balance {
            color: #2563eb;
        }

        .summary {
            margin-top: 25px;
            background: white;
            padding: 25px;
            border-radius: 14px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }

        .back {
            display: inline-block;
            margin-top: 20px;
            color: #2563eb;
            text-decoration: none;
        }

    </style>

</head>

<body>

<%
    Analytics analytics =
        (Analytics) request.getAttribute("analytics");
%>

<div class="navbar">

    <h2>Personal Finance</h2>

    <div>
        <a href="dashboard">Dashboard</a>
        <a href="profile">Profile</a>
        <a href="logout">Logout</a>
    </div>

</div>

<div class="container">

    <h1>📈 Financial Analytics</h1>

    <div class="cards">

        <div class="card">

            <h3>Total Income</h3>

            <div class="amount income">
                ₹<%= String.format("%.2f",
                    analytics.getTotalIncome()) %>
            </div>

        </div>

        <div class="card">

            <h3>Total Expense</h3>

            <div class="amount expense">
                ₹<%= String.format("%.2f",
                    analytics.getTotalExpense()) %>
            </div>

        </div>

        <div class="card">

            <h3>Balance</h3>

            <div class="amount balance">
                ₹<%= String.format("%.2f",
                    analytics.getBalance()) %>
            </div>

        </div>

    </div>

    <div class="summary">

        <h2>Income vs Expense</h2>

        <p>
            Income:
            ₹<%= String.format("%.2f",
                analytics.getTotalIncome()) %>
        </p>

        <p>
            Expense:
            ₹<%= String.format("%.2f",
                analytics.getTotalExpense()) %>
        </p>

        <p>
            Remaining Balance:
            ₹<%= String.format("%.2f",
                analytics.getBalance()) %>
        </p>

    </div>

    <a class="back" href="dashboard">
        ← Back to Dashboard
    </a>

</div>

</body>

</html>