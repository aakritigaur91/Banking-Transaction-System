
package com.banking;

import com.banking.dao.TransactionDAO;
import com.banking.dao.FailedTransactionDAO;
import com.banking.model.Transaction;
import com.banking.model.FailedTransaction;

import java.math.BigDecimal;

public class FailedTransactionDAOTest {

    public static void main(String[] args) {

        String transactionId = "TEST-20261127-002";

        // First, save the rejected transaction
        Transaction transaction = new Transaction();

        transaction.setTransactionId(transactionId);
        transaction.setSourceAccountId(1L);
        transaction.setDestinationAccountId(null);
        transaction.setTransactionType("WITHDRAWAL");
        transaction.setAmount(new BigDecimal("20000.00"));
        transaction.setStatus("REJECTED");

        TransactionDAO transactionDAO = new TransactionDAO();

        boolean transactionSaved =
                transactionDAO.saveTransaction(transaction);

        if (!transactionSaved) {
            System.out.println("Could not save transaction.");
            return;
        }

        // Now, save the rejection details
        FailedTransaction failedTransaction = new FailedTransaction();

        failedTransaction.setTransactionId(transactionId);
        failedTransaction.setErrorCategory("INSUFFICIENT_BALANCE");
        failedTransaction.setReason(
                "Account has insufficient balance for withdrawal."
        );

        FailedTransactionDAO failedDAO = new FailedTransactionDAO();

        boolean result = failedDAO.saveFailedTransaction(failedTransaction);

        if (result) {
            System.out.println("Failed transaction record saved successfully!");
        } else {
            System.out.println("Failed transaction record could not be saved.");
        }
    }
}
