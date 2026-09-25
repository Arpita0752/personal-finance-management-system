package com.finance.dao;

import com.finance.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {

    public void viewCategories() {

        String sql = "SELECT category_id, category_name, category_type " +
                     "FROM EXPENSE_CATEGORY ORDER BY category_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println(
                    resultSet.getInt("category_id") + " | " +
                    resultSet.getString("category_name") + " | " +
                    resultSet.getString("category_type")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}