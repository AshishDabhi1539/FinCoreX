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
    role VARCHAR(20) DEFAULT 'CUSTOMER',
    status VARCHAR(20) DEFAULT 'PENDING',
    last_login TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    email_notification BOOLEAN DEFAULT TRUE,
    sms_notification BOOLEAN DEFAULT TRUE,
    whatsapp_notification BOOLEAN DEFAULT TRUE,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_status (status),
    INDEX idx_role (role)
);

-- Accounts table
CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    ifsc VARCHAR(11) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    account_type VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    branch_code VARCHAR(10),
    interest_rate DECIMAL(5,2) DEFAULT 0.00,
    last_transaction_date TIMESTAMP NULL,
    transaction_count INT DEFAULT 0,
    daily_limit DECIMAL(15,2) DEFAULT 50000.00,
    monthly_limit DECIMAL(15,2) DEFAULT 1000000.00,
    currency VARCHAR(3) DEFAULT 'INR',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_account_number (account_number)
);

-- Transactions table
CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL, -- DEPOSIT, WITHDRAWAL, TRANSFER_OUT, TRANSFER_IN
    amount DECIMAL(15,2) NOT NULL,
    description TEXT,
    txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE,
    INDEX idx_account_id (account_id),
    INDEX idx_type (type),
    INDEX idx_txn_date (txn_date)
);

-- Transfers table
CREATE TABLE transfers (
    transfer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_user_id BIGINT NOT NULL,
    to_user_id BIGINT NOT NULL,
    from_account_id BIGINT NOT NULL,
    to_account_id BIGINT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT 'PENDING',
    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (from_user_id) REFERENCES users(user_id),
    FOREIGN KEY (to_user_id) REFERENCES users(user_id),
    FOREIGN KEY (from_account_id) REFERENCES accounts(id),
    FOREIGN KEY (to_account_id) REFERENCES accounts(id),
    INDEX idx_from_user (from_user_id),
    INDEX idx_to_user (to_user_id),
    INDEX idx_from_account (from_account_id),
    INDEX idx_to_account (to_account_id),
    INDEX idx_status (status)
);

-- Other tables (unchanged)
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

CREATE TABLE loans (
    loan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    loan_type VARCHAR(50) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    tenure_months INT NOT NULL,
    emi_amount DECIMAL(15,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    approved_by BIGINT,
    approved_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (approved_by) REFERENCES users(user_id),
    INDEX idx_user_id (user_id),
    INDEX idx_status (status)
);

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
    address, aadhaar, pan, account_type, role, status
) VALUES (
    'System Administrator', 'admin', 'admin123',
    'admin@mybank.com', '9876543210', '1990-01-01', 'Male',
    'Bank Headquarters, Mumbai', '123456789012', 'ABCDE1234F',
    'Current', 'ADMIN', 'ACTIVE'
);

-- Insert sample branch
INSERT INTO branches (
    branch_name, branch_code, address, city, state, pincode, phone, email, manager_name
) VALUES (
    'Main Branch', 'MB001', '123 Banking Street, Financial District',
    'Mumbai', 'Maharashtra', '400001', '022-12345678',
    'main.branch@mybank.com', 'John Manager'
);

-- Create indexes
CREATE INDEX idx_users_status_role ON users(status, role);
CREATE INDEX idx_transactions_account_date ON transactions(account_id, txn_date);
CREATE INDEX idx_loans_user_status ON loans(user_id, status);

-- Create views
CREATE VIEW active_customers AS
SELECT user_id, full_name, username, email, phone, account_type, status
FROM users 
WHERE role = 'CUSTOMER' AND status = 'ACTIVE';

CREATE VIEW transaction_summary AS
SELECT 
    account_id,
    COUNT(*) as total_transactions,
    SUM(CASE WHEN type = 'DEPOSIT' THEN amount ELSE 0 END) as total_deposits,
    SUM(CASE WHEN type = 'WITHDRAWAL' THEN amount ELSE 0 END) as total_withdrawals,
    SUM(CASE WHEN type IN ('TRANSFER_OUT', 'TRANSFER_IN') THEN amount ELSE 0 END) as total_transfers
FROM transactions 
GROUP BY account_id;

-- Create stored procedures
DELIMITER //
CREATE PROCEDURE GetUserTransactions(IN p_account_id BIGINT, IN p_limit INT)
BEGIN
    SELECT t.*, u.full_name, u.username
    FROM transactions t
    JOIN accounts a ON t.account_id = a.id
    JOIN users u ON a.user_id = u.user_id
    WHERE t.account_id = p_account_id
    ORDER BY t.txn_date DESC
    LIMIT p_limit;
END //

CREATE PROCEDURE GetMonthlyStats(IN p_year INT, IN p_month INT)
BEGIN
    SELECT 
        COUNT(*) as total_transactions,
        SUM(CASE WHEN type = 'DEPOSIT' THEN amount ELSE 0 END) as total_deposits,
        SUM(CASE WHEN type = 'WITHDRAWAL' THEN amount ELSE 0 END) as total_withdrawals,
        SUM(CASE WHEN type IN ('TRANSFER_OUT', 'TRANSFER_IN') THEN amount ELSE 0 END) as total_transfers
    FROM transactions 
    WHERE YEAR(txn_date) = p_year AND MONTH(txn_date) = p_month;
END //
DELIMITER ;