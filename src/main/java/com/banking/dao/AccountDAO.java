package com.banking.dao;

import com.banking.database.DBConnection;
import com.banking.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public Account getAccountById(long accountId) {

        String sql = "SELECT * FROM ACCOUNT WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, accountId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();

                account.setAccountId(resultSet.getLong("account_id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setStatus(resultSet.getString("status"));

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}