package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.SavingsGoal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavingsGoalDAO {

    public boolean addGoal(SavingsGoal goal) {

        String sql = "INSERT INTO SAVINGS_GOAL " +
                     "(goal_id, user_id, goal_name, target_amount, target_date, status) " +
                     "VALUES (savings_goal_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, goal.getUserId());
            statement.setString(2, goal.getGoalName());
            statement.setDouble(3, goal.getTargetAmount());
            statement.setDate(4, goal.getTargetDate());
            statement.setString(5, goal.getStatus());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}