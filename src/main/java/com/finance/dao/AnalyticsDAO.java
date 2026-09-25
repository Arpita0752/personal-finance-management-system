package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.Analytics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnalyticsDAO {

    public Analytics getAnalytics(int userId) {

        Analytics analytics = new Analytics();

        String incomeSql =
                "SELECT NVL(SUM(amount),0) FROM INCOME WHERE user_id = ?";

        String expenseSql =
                "SELECT NVL(SUM(amount),0) FROM EXPENSE WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection()) {

            try (PreparedStatement statement =
                         connection.prepareStatement(incomeSql)) {

                statement.setInt(1, userId);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    analytics.setTotalIncome(rs.getDouble(1));
                }
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(expenseSql)) {

                statement.setInt(1, userId);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    analytics.setTotalExpense(rs.getDouble(1));
                }
            }

            analytics.setBalance(
                    analytics.getTotalIncome()
                    - analytics.getTotalExpense()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return analytics;
    }
}