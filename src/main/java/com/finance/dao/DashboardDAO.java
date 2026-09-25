package com.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.finance.DBConnection;

public class DashboardDAO {

    public double getTotalIncome(int userId) {

        String sql = "SELECT NVL(SUM(amount), 0) FROM INCOME WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getTotalExpense(int userId) {

        String sql = "SELECT NVL(SUM(amount), 0) FROM EXPENSE WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public double getBudgetUsed(int userId) {

        String sql = "SELECT NVL((SELECT SUM(amount) FROM EXPENSE WHERE user_id = ? AND EXTRACT(MONTH FROM expense_date) = EXTRACT(MONTH FROM SYSDATE) AND EXTRACT(YEAR FROM expense_date) = EXTRACT(YEAR FROM SYSDATE)), 0) / NULLIF((SELECT budget_amount FROM BUDGET WHERE user_id = ? AND category_id IS NULL AND budget_month = EXTRACT(MONTH FROM SYSDATE) AND budget_year = EXTRACT(YEAR FROM SYSDATE)), 0) * 100 FROM dual";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public double getSavingsProgress(int userId) {

        String sql = "SELECT NVL((SELECT SUM(gc.amount) " +
                     "FROM GOAL_CONTRIBUTION gc " +
                     "JOIN SAVINGS_GOAL sg ON gc.goal_id = sg.goal_id " +
                     "WHERE sg.user_id = ?), 0) " +
                     "/ NULLIF((SELECT SUM(target_amount) " +
                     "FROM SAVINGS_GOAL " +
                     "WHERE user_id = ?), 0) * 100 " +
                     "FROM dual";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}