package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public List<Transaction> getTransactions(int userId) {

        List<Transaction> transactions = new ArrayList<>();

        String sql =
            "SELECT 'Income' AS type, i.amount, i.income_date AS transaction_date, " +
            "s.source_name AS category, i.description " +
            "FROM INCOME i " +
            "JOIN INCOME_SOURCE s ON i.source_id = s.source_id " +
            "WHERE i.user_id = ? " +

            "UNION ALL " +

            "SELECT 'Expense' AS type, e.amount, e.expense_date AS transaction_date, " +
            "c.category_name AS category, e.description " +
            "FROM EXPENSE e " +
            "JOIN EXPENSE_CATEGORY c ON e.category_id = c.category_id " +
            "WHERE e.user_id = ? " +

            "ORDER BY transaction_date DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Transaction transaction = new Transaction();

                transaction.setType(resultSet.getString("type"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTransactionDate(
                        resultSet.getDate("transaction_date"));
                transaction.setCategory(
                        resultSet.getString("category"));
                transaction.setDescription(
                        resultSet.getString("description"));

                transactions.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
}