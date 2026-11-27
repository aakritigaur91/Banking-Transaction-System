
package com.banking;

import com.banking.dao.TransactionAuditDAO;
import com.banking.model.TransactionAudit;

import java.time.LocalDateTime;

public class TransactionAuditDAOTest {

    public static void main(String[] args) {

        TransactionAudit audit = new TransactionAudit();

        audit.setTransactionId("TEST-20261127-001");
        audit.setEvent("TRANSACTION_COMMITTED");
        audit.setTimestamp(LocalDateTime.now());
        audit.setThreadName(Thread.currentThread().getName());
        audit.setDuration(150L);

        TransactionAuditDAO auditDAO = new TransactionAuditDAO();

        boolean result = auditDAO.saveAudit(audit);

        if (result) {
            System.out.println("Audit record saved successfully!");
        } else {
            System.out.println("Audit record could not be saved.");
        }
    }
}
