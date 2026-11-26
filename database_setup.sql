
-- ==========================================
-- BANKING TRANSACTION PROCESSING SYSTEM
-- Database Setup Script
-- ==========================================

CREATE DATABASE IF NOT EXISTS banking_system;
USE banking_system;


-- 1. CUSTOMER TABLE
CREATE TABLE CUSTOMER (
                          customer_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          phone VARCHAR(15) NOT NULL
);


-- 2. ACCOUNT TABLE
CREATE TABLE ACCOUNT (
                         account_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         customer_id INT NOT NULL,
                         balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
                         status ENUM('ACTIVE', 'INACTIVE', 'BLOCKED')
        NOT NULL DEFAULT 'ACTIVE',

                         CONSTRAINT fk_account_customer
                             FOREIGN KEY (customer_id)
                                 REFERENCES CUSTOMER(customer_id),

                         CONSTRAINT chk_account_balance
                             CHECK (balance >= 0)
);


-- 3. TRANSACTION TABLE
CREATE TABLE `TRANSACTION` (
                               transaction_id CHAR(36) PRIMARY KEY,
                               source_account_id BIGINT NULL,
                               destination_account_id BIGINT NULL,
                               transaction_type ENUM('DEPOSIT', 'WITHDRAWAL', 'TRANSFER')
        NOT NULL,
                               amount DECIMAL(15,2) NOT NULL,
                               status ENUM('PENDING', 'SUCCESS', 'FAILED', 'REJECTED')
        NOT NULL DEFAULT 'PENDING',
                               created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),

                               CONSTRAINT fk_transaction_source
                                   FOREIGN KEY (source_account_id)
                                       REFERENCES ACCOUNT(account_id),

                               CONSTRAINT fk_transaction_destination
                                   FOREIGN KEY (destination_account_id)
                                       REFERENCES ACCOUNT(account_id),

                               CONSTRAINT chk_transaction_amount
                                   CHECK (amount > 0),

                               CONSTRAINT chk_transaction_accounts
                                   CHECK (
                                       (transaction_type = 'DEPOSIT'
                                           AND source_account_id IS NULL
                                           AND destination_account_id IS NOT NULL)
                                           OR
                                       (transaction_type = 'WITHDRAWAL'
                                           AND source_account_id IS NOT NULL
                                           AND destination_account_id IS NULL)
                                           OR
                                       (transaction_type = 'TRANSFER'
                                           AND source_account_id IS NOT NULL
                                           AND destination_account_id IS NOT NULL
                                           AND source_account_id <> destination_account_id)
                                       )
);


-- 4. TRANSACTION INDEXES
CREATE INDEX idx_transaction_source
    ON `TRANSACTION`(source_account_id);

CREATE INDEX idx_transaction_destination
    ON `TRANSACTION`(destination_account_id);


-- 5. TRANSACTION_AUDIT TABLE
CREATE TABLE TRANSACTION_AUDIT (
                                   audit_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                   transaction_id CHAR(36) NOT NULL,
                                   event VARCHAR(50) NOT NULL,
                                   timestamp TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                   thread_name VARCHAR(100),
                                   duration BIGINT,

                                   CONSTRAINT fk_audit_transaction
                                       FOREIGN KEY (transaction_id)
                                           REFERENCES `TRANSACTION`(transaction_id)
);


-- 6. FAILED_TRANSACTION TABLE
CREATE TABLE FAILED_TRANSACTION (
                                    failure_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    transaction_id CHAR(36) NOT NULL UNIQUE,
                                    error_category VARCHAR(50) NOT NULL,
                                    reason VARCHAR(255) NOT NULL,

                                    CONSTRAINT fk_failed_transaction
                                        FOREIGN KEY (transaction_id)
                                            REFERENCES `TRANSACTION`(transaction_id)
);


-- ==========================================
-- SAMPLE DATA
-- ==========================================

-- 7. INSERT CUSTOMERS
INSERT INTO CUSTOMER (name, email, phone)
VALUES
    ('Aarav Sharma', 'aarav@gmail.com', '9876543210'),
    ('Priya Verma', 'priya@gmail.com', '9876543211'),
    ('Rahul Singh', 'rahul@gmail.com', '9876543212'),
    ('Ananya Gupta', 'ananya@gmail.com', '9876543213'),
    ('Rohan Mehta', 'rohan@gmail.com', '9876543214');


-- 8. INSERT FIRST ACCOUNT FOR EACH CUSTOMER
INSERT INTO ACCOUNT (customer_id, balance, status)
SELECT
    customer_id,
    CASE email
        WHEN 'aarav@gmail.com' THEN 15000.00
        WHEN 'priya@gmail.com' THEN 25000.00
        WHEN 'rahul@gmail.com' THEN 10000.00
        WHEN 'ananya@gmail.com' THEN 30000.00
        WHEN 'rohan@gmail.com' THEN 5000.00
        END,
    'ACTIVE'
FROM CUSTOMER
WHERE email IN (
                'aarav@gmail.com',
                'priya@gmail.com',
                'rahul@gmail.com',
                'ananya@gmail.com',
                'rohan@gmail.com'
    );


-- 9. INSERT A SECOND ACCOUNT FOR AARAV
INSERT INTO ACCOUNT (customer_id, balance, status)
SELECT customer_id, 5000.00, 'ACTIVE'
FROM CUSTOMER
WHERE email = 'aarav@gmail.com';
