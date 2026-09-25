package com.finance.service;

import com.finance.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinanceAnalyticsService {

    public double getTotalIncome(int userId) {

        String sql = "SELECT NVL(SUM(amount), 0) FROM INCOME WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getTotalExpense(int userId) {

        String sql = "SELECT NVL(SUM(amount), 0) FROM EXPENSE WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getBalance(int userId) {

        double totalIncome = getTotalIncome(userId);
        double totalExpense = getTotalExpense(userId);

        return totalIncome - totalExpense;
    }
    
    public void getCategoryWiseExpense(int userId) {

        String sql = "SELECT c.category_name, NVL(SUM(e.amount), 0) AS total_expense " +
                     "FROM EXPENSE e " +
                     "JOIN EXPENSE_CATEGORY c ON e.category_id = c.category_id " +
                     "WHERE e.user_id = ? " +
                     "GROUP BY c.category_name " +
                     "ORDER BY total_expense DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                    resultSet.getString("category_name") +
                    " : ₹" +
                    resultSet.getDouble("total_expense")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void getBudgetUtilization(int userId, int month, int year) {

        String sql = "SELECT b.budget_amount, " +
                     "NVL(SUM(e.amount), 0) AS spent_amount, " +
                     "ROUND((NVL(SUM(e.amount), 0) / b.budget_amount) * 100, 2) AS budget_used_percent, " +
                     "CASE " +
                     "WHEN (NVL(SUM(e.amount), 0) / b.budget_amount) * 100 <= 75 THEN 'GREEN' " +
                     "WHEN (NVL(SUM(e.amount), 0) / b.budget_amount) * 100 <= 100 THEN 'WARNING' " +
                     "ELSE 'OVER BUDGET' END AS budget_zone " +
                     "FROM BUDGET b " +
                     "LEFT JOIN EXPENSE e ON b.user_id = e.user_id " +
                     "AND b.category_id = e.category_id " +
                     "AND EXTRACT(MONTH FROM e.expense_date) = b.budget_month " +
                     "AND EXTRACT(YEAR FROM e.expense_date) = b.budget_year " +
                     "WHERE b.user_id = ? " +
                     "AND b.category_id IS NOT NULL " +
                     "AND b.budget_month = ? " +
                     "AND b.budget_year = ? " +
                     "GROUP BY b.budget_amount";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, month);
            statement.setInt(3, year);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Budget       : ₹" + resultSet.getDouble("budget_amount"));
                System.out.println("Spent        : ₹" + resultSet.getDouble("spent_amount"));
                System.out.println("Used         : " + resultSet.getDouble("budget_used_percent") + "%");
                System.out.println("Budget Zone  : " + resultSet.getString("budget_zone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void getSavingsGoalProgress(int userId) {

        String sql = "SELECT g.goal_name, g.target_amount, " +
                     "NVL(SUM(c.amount), 0) AS saved_amount, " +
                     "ROUND((NVL(SUM(c.amount), 0) / g.target_amount) * 100, 2) AS progress_percent " +
                     "FROM SAVINGS_GOAL g " +
                     "LEFT JOIN GOAL_CONTRIBUTION c ON g.goal_id = c.goal_id " +
                     "WHERE g.user_id = ? " +
                     "GROUP BY g.goal_name, g.target_amount";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Goal         : " + resultSet.getString("goal_name"));
                System.out.println("Target       : ₹" + resultSet.getDouble("target_amount"));
                System.out.println("Saved        : ₹" + resultSet.getDouble("saved_amount"));
                System.out.println("Progress     : " + resultSet.getDouble("progress_percent") + "%");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void getMonthlyExpenseComparison(int userId, int currentMonth, int currentYear) {

        String sql = "SELECT " +
                     "NVL(SUM(CASE WHEN EXTRACT(MONTH FROM expense_date) = ? AND EXTRACT(YEAR FROM expense_date) = ? THEN amount END), 0) AS current_month_expense, " +
                     "NVL(SUM(CASE WHEN EXTRACT(MONTH FROM expense_date) = ? AND EXTRACT(YEAR FROM expense_date) = ? THEN amount END), 0) AS previous_month_expense " +
                     "FROM EXPENSE WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int previousMonth = currentMonth - 1;
            int previousYear = currentYear;

            if (previousMonth == 0) {
                previousMonth = 12;
                previousYear--;
            }

            statement.setInt(1, currentMonth);
            statement.setInt(2, currentYear);
            statement.setInt(3, previousMonth);
            statement.setInt(4, previousYear);
            statement.setInt(5, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                double currentExpense =
                        resultSet.getDouble("current_month_expense");

                double previousExpense =
                        resultSet.getDouble("previous_month_expense");

                System.out.println("Current Month Expense  : ₹" + currentExpense);
                System.out.println("Previous Month Expense: ₹" + previousExpense);

                double difference = currentExpense - previousExpense;

                System.out.println("Difference             : ₹" + difference);

                if (previousExpense > 0) {
                    double percentage =
                            (difference / previousExpense) * 100;

                    System.out.println("Percentage Change      : " +
                                       Math.round(percentage * 100.0) / 100.0 + "%");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}