<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Personal Finance Dashboard</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            background: #f4f6f9;
            color: #333;
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
            font-size: 22px;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 35px auto;
        }

        .welcome {
            margin-bottom: 25px;
        }

        .welcome h1 {
            font-size: 28px;
            margin-bottom: 8px;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 18px;
        }

        .card {
            background: white;
            padding: 22px;
            border-radius: 12px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.08);
        }

        .card h3 {
            font-size: 15px;
            color: #666;
            margin-bottom: 12px;
        }

        .amount {
            font-size: 24px;
            font-weight: bold;
        }

        .actions {
            margin-top: 35px;
        }

        .actions h2 {
            margin-bottom: 18px;
        }

        .buttons {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 15px;
        }

        .buttons a {
            background: white;
            padding: 18px;
            text-align: center;
            text-decoration: none;
            color: #333;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.07);
            font-weight: bold;
        }

        .buttons a:hover {
            background: #e9eef5;
        }

        .progress {
            margin-top: 10px;
            background: #e5e7eb;
            height: 8px;
            border-radius: 10px;
            overflow: hidden;
        }

        .progress-bar {
            height: 100%;
            background: #2563eb;
        }

        @media (max-width: 900px) {

            .cards {
                grid-template-columns: repeat(2, 1fr);
            }

            .buttons {
                grid-template-columns: repeat(2, 1fr);
            }

        }

    </style>

</head>

<body>

<div class="navbar">

    <h2>Personal Finance Management</h2>

    <div class="nav-links">

        <a href="profile">Profile</a>

        <a href="logout">Logout</a>

    </div>

</div>


<div class="container">

    <div class="welcome">

        <h1>Welcome, ${userName} 👋</h1>

        <p>Manage your income, expenses and savings in one place.</p>

    </div>


    <div class="cards">

        <div class="card">

            <h3>Total Income</h3>

            <div class="amount">
                ₹<%= String.format("%.0f",
                    (Double)request.getAttribute("totalIncome")) %>
            </div>

        </div>


        <div class="card">

            <h3>Total Expense</h3>

            <div class="amount">
                ₹<%= String.format("%.0f",
                    (Double)request.getAttribute("totalExpense")) %>
            </div>

        </div>


        <div class="card">

            <h3>Balance</h3>

            <div class="amount">
                ₹<%= String.format("%.0f",
                    (Double)request.getAttribute("balance")) %>
            </div>

        </div>


        <div class="card">

            <h3>Budget Used</h3>

            <div class="amount">
                <%= String.format("%.2f",
                    (Double)request.getAttribute("budgetUsed")) %>%
            </div>

            <div class="progress">

                <div class="progress-bar"
                     style="width:<%= Math.min(
                         (Double)request.getAttribute("budgetUsed"), 100) %>%">
                </div>

            </div>

        </div>


        <div class="card">

            <h3>Savings Progress</h3>

            <div class="amount">
                <%= String.format("%.2f",
                    (Double)request.getAttribute("savingsProgress")) %>%
            </div>

            <div class="progress">

                <div class="progress-bar"
                     style="width:<%= Math.min(
                         (Double)request.getAttribute("savingsProgress"), 100) %>%">
                </div>

            </div>

        </div>

    </div>


    <div class="actions">

        <h2>Quick Actions</h2>

        <div class="buttons">

            <a href="add-income.html">
                ➕ Add Income
            </a>

            <a href="add-expense.html">
                ➖ Add Expense
            </a>

            <a href="add-budget.html">
                💰 Set Budget
            </a>

            <a href="add-savings-goal.html">
                🎯 Savings Goal
            </a>

            <a href="add-contribution.html">
                💵 Add Contribution
            </a>

            <a href="transactions">
                📋 Transactions
            </a>

            <a href="analytics">
                📈 Analytics
            </a>

            <a href="profile">
                👤 My Profile
            </a>

        </div>

    </div>

</div>

</body>

</html>