package com.banking;

import com.banking.dao.AccountDAO;
import com.banking.model.Account;

public class AccountDAOTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account account = accountDAO.getAccountById(1);

        if (account != null) {
            System.out.println("Account found!");
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Customer ID: " + account.getCustomerId());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Status: " + account.getStatus());
        } else {
            System.out.println("Account not found!");
        }
    }
}