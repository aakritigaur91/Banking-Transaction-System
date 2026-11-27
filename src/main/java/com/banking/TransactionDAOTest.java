
package com.banking;

import com.banking.dao.TransactionDAO;
import com.banking.model.Transaction;

import java.math.BigDecimal;

public class TransactionDAOTest {

    public static void main(String[] args) {

        Transaction transaction = new Transaction();

        transaction.setTransactionId("TEST-20261127-001");
        transaction.setSourceAccountId(null);
        transaction.setDestinationAccountId(1L);
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(new BigDecimal("500.00"));
        transaction.setStatus("SUCCESS");

        TransactionDAO transactionDAO = new TransactionDAO();

        boolean result = transactionDAO.saveTransaction(transaction);

        if (result) {
            System.out.println("Transaction saved successfully!");
        } else {
            System.out.println("Transaction could not be saved.");
        }
    }
}
