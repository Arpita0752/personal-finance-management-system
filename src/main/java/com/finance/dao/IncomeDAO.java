package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeDAO {

	public boolean addIncome(Income income) {

	    String sql = "INSERT INTO INCOME " +
	                 "(income_id, user_id, source_id, amount, income_date, income_type, description) " +
	                 "VALUES (income_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, income.getUserId());
	        statement.setInt(2, income.getSourceId());
	        statement.setDouble(3, income.getAmount());
	        statement.setDate(4, income.getIncomeDate());
	        statement.setString(5, income.getIncomeType());
	        statement.setString(6, income.getDescription());

	        return statement.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    public void viewIncomeByUser(int userId) {

        String sql = "SELECT * FROM INCOME WHERE user_id = ? ORDER BY income_date DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                System.out.println(
                    resultSet.getInt("income_id") + " | " +
                    resultSet.getDouble("amount") + " | " +
                    resultSet.getDate("income_date") + " | " +
                    resultSet.getString("income_type") + " | " +
                    resultSet.getString("description")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}