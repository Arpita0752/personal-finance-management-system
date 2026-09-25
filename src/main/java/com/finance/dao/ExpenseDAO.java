package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseDAO {

    public boolean addExpense(Expense expense) {

        String sql = "INSERT INTO EXPENSE " +
                     "(expense_id, user_id, category_id, payment_method_id, amount, expense_date, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, expense.getExpenseId());
            statement.setInt(2, expense.getUserId());
            statement.setInt(3, expense.getCategoryId());
            statement.setInt(4, expense.getPaymentMethodId());
            statement.setDouble(5, expense.getAmount());
            statement.setDate(6, expense.getExpenseDate());
            statement.setString(7, expense.getDescription());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void viewExpenseByUser(int userId) {

        String sql = "SELECT * FROM EXPENSE WHERE user_id = ? ORDER BY expense_date DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                System.out.println(
                    resultSet.getInt("expense_id") + " | " +
                    resultSet.getDouble("amount") + " | " +
                    resultSet.getDate("expense_date") + " | " +
                    resultSet.getString("description")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}