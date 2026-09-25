package com.finance.dao;

import com.finance.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethodDAO {

    public void viewPaymentMethods() {

        String sql = "SELECT payment_method_id, method_name " +
                     "FROM PAYMENT_METHOD ORDER BY payment_method_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println(
                    resultSet.getInt("payment_method_id") + " | " +
                    resultSet.getString("method_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}