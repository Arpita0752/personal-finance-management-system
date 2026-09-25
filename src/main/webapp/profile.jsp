<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.finance.model.User" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>My Profile</title>

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
            max-width: 650px;
            margin: 40px auto;
        }

        .card {
            background: white;
            padding: 30px;
            border-radius: 14px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }

        h1 {
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            margin-bottom: 18px;
            font-size: 15px;
        }

        input[readonly] {
            background: #f3f4f6;
        }

        button {
            width: 100%;
            padding: 13px;
            border: none;
            border-radius: 8px;
            background: #2563eb;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 18px;
            color: #2563eb;
            text-decoration: none;
        }

    </style>

</head>

<body>

<%
    User user = (User) request.getAttribute("user");
%>

<div class="navbar">

    <h2>Personal Finance</h2>

    <div>
        <a href="dashboard">Dashboard</a>
        <a href="logout">Logout</a>
    </div>

</div>

<div class="container">

    <div class="card">

        <h1>👤 My Profile</h1>

        <form action="profile" method="post">

            <label>Full Name</label>

            <input type="text"
                   name="fullName"
                   value="<%= user.getFullName() %>"
                   required>

            <label>Email</label>

            <input type="email"
                   value="<%= user.getEmail() %>"
                   readonly>

            <label>Mobile</label>

            <input type="text"
                   name="mobile"
                   value="<%= user.getMobile() %>"
                   maxlength="10"
                   required>

            <label>Date of Birth</label>

            <input type="date"
                   name="dateOfBirth"
                   value="<%= user.getDateOfBirth() != null
                       ? user.getDateOfBirth() : "" %>">

            <button type="submit">
                Update Profile
            </button>

        </form>

        <a class="back" href="dashboard">
            ← Back to Dashboard
        </a>

    </div>

</div>

</body>

</html>