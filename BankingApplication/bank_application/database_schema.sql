-- Banking Application Database Schema
-- MySQL 8.0+

-- Create database
CREATE DATABASE IF NOT EXISTS banking;
USE banking;

-- Users table
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    dob DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address TEXT NOT NULL,
    aadhaar VARCHAR(12) UNIQUE NOT NULL,
    pan VARCHAR(10) UNIQUE NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    deposit DECIMAL(15,2) DEFAULT 0.00,
    role VARCHAR(20) DEFAULT 'CUSTOMER',
    status VARCHAR(20) DEFAULT 'PENDING',
    last_login TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_status (status),
    INDEX idx_role (role)
);

-- Transactions table
CREATE TABLE transactions (
    txn_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL, -- DEPOSIT, WITHDRAW, TRANSFER_DEBIT, TRANSFER_CREDIT
    amount DECIMAL(15,2) NOT NULL,
    description TEXT,
    txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_type (type),
    INDEX idx_txn_date (txn_date)
);

-- Accounts table (for future expansion)
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    ifsc VARCHAR(11) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    account_type VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_account_number (account_number)
);

-- Customers table (for additional customer-specific data)
CREATE TABLE customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    occupation VARCHAR(100),
    annual_income DECIMAL(15,2),
    source_of_income VARCHAR(100),
    nominee_name VARCHAR(100),
    nominee_relation VARCHAR(50),
    nominee_phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id)
);

-- Branches table
CREATE TABLE branches (
    branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_name VARCHAR(100) NOT NULL,
    branch_code VARCHAR(10) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    pincode VARCHAR(10) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    manager_name VARCHAR(100),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_branch_code (branch_code),
    INDEX idx_city (city)
);

-- Loans table
CREATE TABLE loans (
    loan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    loan_type VARCHAR(50) NOT NULL, -- PERSONAL, HOME, BUSINESS, etc.
    amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    tenure_months INT NOT NULL,
    emi_amount DECIMAL(15,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, APPROVED, REJECTED, ACTIVE, CLOSED
    approved_by BIGINT,
    approved_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (approved_by) REFERENCES users(user_id),
    INDEX idx_user_id (user_id),
    INDEX idx_status (status)
);

-- Beneficiaries table
CREATE TABLE beneficiaries (
    beneficiary_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    beneficiary_name VARCHAR(100) NOT NULL,
    account_number VARCHAR(20) NOT NULL,
    ifsc VARCHAR(11) NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id)
);

-- Transfers table (for detailed transfer tracking)
CREATE TABLE transfers (
    transfer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_user_id BIGINT NOT NULL,
    to_user_id BIGINT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, COMPLETED, FAILED, CANCELLED
    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (from_user_id) REFERENCES users(user_id),
    FOREIGN KEY (to_user_id) REFERENCES users(user_id),
    INDEX idx_from_user (from_user_id),
    INDEX idx_to_user (to_user_id),
    INDEX idx_status (status)
);

-- Notification preferences table
CREATE TABLE notification_preferences (
    preference_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    email_notifications BOOLEAN DEFAULT TRUE,
    sms_notifications BOOLEAN DEFAULT TRUE,
    transaction_alerts BOOLEAN DEFAULT TRUE,
    balance_alerts BOOLEAN DEFAULT TRUE,
    promotional_emails BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    UNIQUE KEY unique_user_preferences (user_id)
);

-- Audit logs table
CREATE TABLE audit_logs (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    action VARCHAR(100) NOT NULL,
    table_name VARCHAR(50),
    record_id BIGINT,
    old_values JSON,
    new_values JSON,
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_user_id (user_id),
    INDEX idx_action (action),
    INDEX idx_created_at (created_at)
);

-- Insert default admin user
INSERT INTO users (
    full_name, username, password_hash, email, phone, dob, gender, 
    address, aadhaar, pan, account_type, deposit, role, status
) VALUES (
    'System Administrator',
    'admin',
    'admin123', -- In production, use proper password hashing
    'admin@mybank.com',
    '9876543210',
    '1990-01-01',
    'Male',
    'Bank Headquarters, Mumbai',
    '123456789012',
    'ABCDE1234F',
    'Current',
    1000000.00,
    'ADMIN',
    'ACTIVE'
);

-- Insert sample branch
INSERT INTO branches (
    branch_name, branch_code, address, city, state, pincode, 
    phone, email, manager_name
) VALUES (
    'Main Branch',
    'MB001',
    '123 Banking Street, Financial District',
    'Mumbai',
    'Maharashtra',
    '400001',
    '022-12345678',
    'main.branch@mybank.com',
    'John Manager'
);

-- Create indexes for better performance
CREATE INDEX idx_users_status_role ON users(status, role);
CREATE INDEX idx_transactions_user_date ON transactions(user_id, txn_date);
CREATE INDEX idx_loans_user_status ON loans(user_id, status);

-- Create views for common queries
CREATE VIEW active_customers AS
SELECT user_id, full_name, username, email, phone, account_type, deposit, status
FROM users 
WHERE role = 'CUSTOMER' AND status = 'ACTIVE';

CREATE VIEW transaction_summary AS
SELECT 
    user_id,
    COUNT(*) as total_transactions,
    SUM(CASE WHEN type = 'DEPOSIT' THEN amount ELSE 0 END) as total_deposits,
    SUM(CASE WHEN type = 'WITHDRAW' THEN amount ELSE 0 END) as total_withdrawals,
    SUM(CASE WHEN type IN ('TRANSFER_DEBIT', 'TRANSFER_CREDIT') THEN amount ELSE 0 END) as total_transfers
FROM transactions 
GROUP BY user_id;

-- Create stored procedures
DELIMITER //

CREATE PROCEDURE GetUserTransactions(IN p_user_id BIGINT, IN p_limit INT)
BEGIN
    SELECT t.*, u.full_name, u.username
    FROM transactions t
    JOIN users u ON t.user_id = u.user_id
    WHERE t.user_id = p_user_id
    ORDER BY t.txn_date DESC
    LIMIT p_limit;
END //

CREATE PROCEDURE GetMonthlyStats(IN p_year INT, IN p_month INT)
BEGIN
    SELECT 
        COUNT(*) as total_transactions,
        SUM(CASE WHEN type = 'DEPOSIT' THEN amount ELSE 0 END) as total_deposits,
        SUM(CASE WHEN type = 'WITHDRAW' THEN amount ELSE 0 END) as total_withdrawals,
        SUM(CASE WHEN type IN ('TRANSFER_DEBIT', 'TRANSFER_CREDIT') THEN amount ELSE 0 END) as total_transfers
    FROM transactions 
    WHERE YEAR(txn_date) = p_year AND MONTH(txn_date) = p_month;
END //

DELIMITER ;

-- Grant permissions (adjust as needed)
-- GRANT ALL PRIVILEGES ON banking.* TO 'banking_user'@'localhost';
-- FLUSH PRIVILEGES;
