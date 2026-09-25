<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.finance.model.Transaction" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Transaction History</title>

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
            width: 92%;
            max-width: 1200px;
            margin: 40px auto;
        }

        .card {
            background: white;
            padding: 30px;
            border-radius: 14px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            overflow-x: auto;
        }

        h1 {
            margin-bottom: 25px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background: #1f2937;
            color: white;
            padding: 14px;
            text-align: left;
        }

        td {
            padding: 13px;
            border-bottom: 1px solid #e5e7eb;
        }

        tr:hover {
            background: #f9fafb;
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

<div class="navbar">

    <h2>Personal Finance</h2>

    <div>
        <a href="dashboard">Dashboard</a>
        <a href="profile">Profile</a>
        <a href="logout">Logout</a>
    </div>

</div>

<div class="container">

    <div class="card">

        <h1>📋 Transaction History</h1>

        <table>

            <tr>
                <th>Type</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Category</th>
                <th>Description</th>
            </tr>

<%
    List<Transaction> transactions =
        (List<Transaction>) request.getAttribute("transactions");

    for (Transaction transaction : transactions) {
%>

            <tr>

                <td><%= transaction.getType() %></td>

                <td>
                    ₹<%= String.format("%.2f",
                        transaction.getAmount()) %>
                </td>

                <td>
                    <%= transaction.getTransactionDate() %>
                </td>

                <td>
                    <%= transaction.getCategory() %>
                </td>

                <td>
                    <%= transaction.getDescription() %>
                </td>

            </tr>

<%
    }
%>

        </table>

        <a class="back" href="dashboard">
            ← Back to Dashboard
        </a>

    </div>

</div>

</body>

</html>