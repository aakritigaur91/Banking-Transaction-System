
package com.banking.dao;

import com.banking.database.DBConnection;
import com.banking.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TransactionDAO {

    public boolean saveTransaction(Transaction transaction) {

        String sql = "INSERT INTO `TRANSACTION` " +
                "(transaction_id, source_account_id, destination_account_id, " +
                "transaction_type, amount, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transaction.getTransactionId());

            if (transaction.getSourceAccountId() != null) {
                statement.setLong(2, transaction.getSourceAccountId());
            } else {
                statement.setNull(2, Types.BIGINT);
            }

            if (transaction.getDestinationAccountId() != null) {
                statement.setLong(3, transaction.getDestinationAccountId());
            } else {
                statement.setNull(3, Types.BIGINT);
            }

            statement.setString(4, transaction.getTransactionType());
            statement.setBigDecimal(5, transaction.getAmount());
            statement.setString(6, transaction.getStatus());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
