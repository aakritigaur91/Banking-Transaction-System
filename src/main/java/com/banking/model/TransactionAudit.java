package com.banking.model;

import java.time.LocalDateTime;

public class TransactionAudit {

    private long auditId;
    private String transactionId;
    private String event;
    private LocalDateTime timestamp;
    private String threadName;
    private Long duration;

    public long getAuditId() {
        return auditId;
    }

    public void setAuditId(long auditId) {
        this.auditId = auditId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public TransactionAudit(String event, long auditId, String transactionId, LocalDateTime timestamp, String threadName, Long duration) {
        this.event = event;
        this.auditId = auditId;
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.duration = duration;
    }

    public TransactionAudit() {
    }
}