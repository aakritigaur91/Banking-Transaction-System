
package com.banking.dao;

import com.banking.database.DBConnection;
import com.banking.model.FailedTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FailedTransactionDAO {

    public boolean saveFailedTransaction(FailedTransaction failedTransaction) {

        String sql = "INSERT INTO FAILED_TRANSACTION " +
                "(transaction_id, error_category, reason) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, failedTransaction.getTransactionId());
            statement.setString(2, failedTransaction.getErrorCategory());
            statement.setString(3, failedTransaction.getReason());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
