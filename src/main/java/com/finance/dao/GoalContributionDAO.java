package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.GoalContribution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GoalContributionDAO {

    public boolean addContribution(GoalContribution contribution) {

        String sql = "INSERT INTO GOAL_CONTRIBUTION " +
                     "(contribution_id, goal_id, amount, contribution_date, description) " +
                     "VALUES (goal_contribution_seq.NEXTVAL, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contribution.getGoalId());
            statement.setDouble(2, contribution.getAmount());
            statement.setDate(3, contribution.getContributionDate());
            statement.setString(4, contribution.getDescription());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}