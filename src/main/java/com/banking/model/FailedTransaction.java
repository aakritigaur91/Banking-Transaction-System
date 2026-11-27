package com.banking.model;

public class FailedTransaction {

    private long failureId;
    private String transactionId;
    private String errorCategory;
    private String reason;

    public long getFailureId() {
        return failureId;
    }

    public void setFailureId(long failureId) {
        this.failureId = failureId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory;
    }

    public FailedTransaction(long failureId, String transactionId, String errorCategory, String reason) {
        this.failureId = failureId;
        this.transactionId = transactionId;
        this.errorCategory = errorCategory;
        this.reason = reason;
    }

    public FailedTransaction() {
    }
}