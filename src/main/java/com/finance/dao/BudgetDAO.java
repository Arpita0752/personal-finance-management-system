package com.finance.dao;

import com.finance.DBConnection;
import com.finance.model.Budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BudgetDAO {

	public boolean addBudget(Budget budget) {

	    String sql = "INSERT INTO BUDGET " +
	                 "(budget_id, user_id, category_id, budget_amount, budget_month, budget_year) " +
	                 "VALUES (budget_seq.NEXTVAL, ?, ?, ?, ?, ?)";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, budget.getUserId());

	        if (budget.getCategoryId() == 0) {
	            statement.setNull(2, java.sql.Types.INTEGER);
	        } else {
	            statement.setInt(2, budget.getCategoryId());
	        }

	        statement.setDouble(3, budget.getBudgetAmount());
	        statement.setInt(4, budget.getBudgetMonth());
	        statement.setInt(5, budget.getBudgetYear());

	        return statement.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}