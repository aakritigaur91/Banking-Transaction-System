
package com.banking.dao;

import com.banking.database.DBConnection;
import com.banking.model.TransactionAudit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionAuditDAO {

    public boolean saveAudit(TransactionAudit audit) {

        String sql = "INSERT INTO TRANSACTION_AUDIT " +
                "(transaction_id, event, timestamp, thread_name, duration) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, audit.getTransactionId());
            statement.setString(2, audit.getEvent());
            statement.setTimestamp(3, Timestamp.valueOf(audit.getTimestamp()));
            statement.setString(4, audit.getThreadName());

            if (audit.getDuration() != null) {
                statement.setLong(5, audit.getDuration());
            } else {
                statement.setNull(5, java.sql.Types.BIGINT);
            }

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
