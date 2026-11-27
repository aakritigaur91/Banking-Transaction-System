package com.banking.model;

import java.math.BigDecimal;

public class Account {

    private long accountId;
    private int customerId;
    private BigDecimal balance;
    private String status;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account(long accountId, int customerId, BigDecimal balance, String status) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
        this.status = status;
    }

    public Account() {
    }
}