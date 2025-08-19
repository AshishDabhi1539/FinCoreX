	-- ─────────────────────────────────────────────────────────
	-- 0) DATABASE
	-- ─────────────────────────────────────────────────────────
	CREATE DATABASE IF NOT EXISTS banking
	  DEFAULT CHARACTER SET utf8mb4
	  DEFAULT COLLATE utf8mb4_0900_ai_ci;
	USE banking;

	SET default_storage_engine = InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 1) REFERENCE: BRANCHES
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE branches (
	  branch_id      BIGINT PRIMARY KEY AUTO_INCREMENT,
	  branch_name    VARCHAR(120) NOT NULL,
	  ifsc_code      VARCHAR(20)  NOT NULL UNIQUE,
	  address        VARCHAR(255) NOT NULL,
	  contact_number VARCHAR(20),
	  created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 2) USERS (AUTHN/AUTHZ)
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE users (
	  user_id        BIGINT PRIMARY KEY AUTO_INCREMENT,
	  username       VARCHAR(60)  NOT NULL,
	  password_hash  VARCHAR(100) NOT NULL,  -- bcrypt ~60 chars
	  email          VARCHAR(120) NOT NULL,
	  phone          VARCHAR(20)  NOT NULL,
	  role           ENUM('CUSTOMER','ADMIN','AUDITOR') NOT NULL DEFAULT 'CUSTOMER',
	  status         ENUM('ACTIVE','SUSPENDED','CLOSED') NOT NULL DEFAULT 'ACTIVE',
	  last_login     DATETIME NULL,
	  created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  UNIQUE KEY uk_users_username (username),
	  UNIQUE KEY uk_users_email    (email),
	  UNIQUE KEY uk_users_phone    (phone)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 3) CUSTOMERS (KYC/PII)
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE customers (
	  customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	  user_id     BIGINT NOT NULL,
	  full_name   VARCHAR(150) NOT NULL,
	  dob         DATE,
	  address     VARCHAR(255),
	  email       VARCHAR(120),
	  phone       VARCHAR(20),
	  pan_no      VARCHAR(20),   -- (store encrypted at app-layer if needed)
	  kyc_status  ENUM('PENDING','VERIFIED','REJECTED') NOT NULL DEFAULT 'PENDING',
	  created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  CONSTRAINT fk_customers_users
		FOREIGN KEY (user_id) REFERENCES users(user_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT,
	  INDEX idx_customers_user (user_id),
	  UNIQUE KEY uk_customers_pan (pan_no)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 4) ACCOUNTS
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE accounts (
	  account_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
	  customer_id    BIGINT NOT NULL,
	  branch_id      BIGINT NOT NULL,
	  account_number BIGINT NOT NULL,
	  account_type   ENUM('SAVINGS','CURRENT','FIXED_DEPOSIT') NOT NULL,
	  currency       CHAR(3) NOT NULL DEFAULT 'INR',           -- ISO 4217
	  status         ENUM('ACTIVE','DORMANT','CLOSED') NOT NULL DEFAULT 'ACTIVE',
	  balance        DECIMAL(18,2) NOT NULL DEFAULT 0.00,
	  created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  CONSTRAINT fk_accounts_customer
		FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT,
	  CONSTRAINT fk_accounts_branch
		FOREIGN KEY (branch_id) REFERENCES branches(branch_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT,
	  UNIQUE KEY uk_accounts_number (account_number),
	  INDEX idx_accounts_customer (customer_id),
	  INDEX idx_accounts_status (status)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 5) BENEFICIARIES (PAYEES)
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE beneficiaries (
	  beneficiary_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
	  account_id       BIGINT NOT NULL,                -- owner account
	  beneficiary_name VARCHAR(150) NOT NULL,
	  beneficiary_acno BIGINT NOT NULL,
	  ifsc_code        VARCHAR(20) NOT NULL,
	  status           ENUM('PENDING','ACTIVE','BLOCKED') NOT NULL DEFAULT 'PENDING',
	  added_at         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  activated_at     TIMESTAMP NULL,
	  CONSTRAINT fk_beneficiaries_account
		FOREIGN KEY (account_id) REFERENCES accounts(account_id)
		  ON UPDATE CASCADE ON DELETE CASCADE,
	  INDEX idx_beneficiaries_account (account_id),
	  INDEX idx_beneficiaries_status (status)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 6) TRANSFERS (REQUEST HEADER, IDEMPOTENT)
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE transfers (
	  transfer_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
	  from_account_id BIGINT NOT NULL,
	  to_acno         BIGINT NOT NULL,
	  to_ifsc         VARCHAR(20) NOT NULL,
	  scheme          ENUM('SELF','INTERNAL','IMPS','NEFT','RTGS','UPI') NOT NULL DEFAULT 'INTERNAL',
	  amount          DECIMAL(18,2) NOT NULL CHECK (amount > 0),
	  fee             DECIMAL(18,2) NOT NULL DEFAULT 0.00,
	  currency        CHAR(3) NOT NULL DEFAULT 'INR',
	  beneficiary_id  BIGINT NULL,
	  external_id     VARCHAR(64) NOT NULL,     -- client idempotency key
	  status          ENUM('PENDING','SUCCESS','FAILED') NOT NULL DEFAULT 'PENDING',
	  reason_code     VARCHAR(64) NULL,
	  created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  completed_at    DATETIME NULL,
	  CONSTRAINT fk_transfers_from
		FOREIGN KEY (from_account_id) REFERENCES accounts(account_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT,
	  CONSTRAINT fk_transfers_benef
		FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries(beneficiary_id)
		  ON UPDATE CASCADE ON DELETE SET NULL,
	  UNIQUE KEY uk_transfers_external (external_id),
	  INDEX idx_transfers_from (from_account_id, created_at),
	  INDEX idx_transfers_status (status)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 7) TRANSACTIONS (LEDGER, IMMUTABLE ROWS)
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE transactions (
	  txn_id       BIGINT PRIMARY KEY AUTO_INCREMENT,
	  account_id   BIGINT NOT NULL,
	  direction    ENUM('DEBIT','CREDIT') NOT NULL,
	  mode         ENUM('INTERNAL','IMPS','NEFT','RTGS','UPI','CASH','CHEQUE') NOT NULL DEFAULT 'INTERNAL',
	  amount       DECIMAL(18,2) NOT NULL CHECK (amount > 0),
	  currency     CHAR(3) NOT NULL DEFAULT 'INR',
	  description  VARCHAR(255),
	  counterparty VARCHAR(120),
	  ref_no       VARCHAR(64) NOT NULL,
	  status       ENUM('PENDING','SUCCESS','FAILED','REVERSAL') NOT NULL DEFAULT 'SUCCESS',
	  txn_time     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  CONSTRAINT fk_txn_account
		FOREIGN KEY (account_id) REFERENCES accounts(account_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT,
	  UNIQUE KEY uk_transactions_ref (ref_no),
	  INDEX idx_txn_account_time (account_id, txn_time),
	  INDEX idx_txn_status (status)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 8) OPTIONAL: LOANS
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE loans (
	  loan_id        BIGINT PRIMARY KEY AUTO_INCREMENT,
	  customer_id    BIGINT NOT NULL,
	  loan_type      ENUM('HOME','CAR','PERSONAL','EDUCATION') NOT NULL,
	  amount         DECIMAL(18,2) NOT NULL CHECK (amount > 0),
	  interest_rate  DECIMAL(5,2) NOT NULL CHECK (interest_rate >= 0),
	  tenure_months  INT NOT NULL CHECK (tenure_months > 0),
	  status         ENUM('PENDING','APPROVED','REJECTED','DISBURSED','CLOSED') NOT NULL DEFAULT 'PENDING',
	  applied_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  approved_by    BIGINT NULL, -- admin user
	  approved_date  DATETIME NULL,
	  CONSTRAINT fk_loans_customer
		FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT,
	  CONSTRAINT fk_loans_approvedby
		FOREIGN KEY (approved_by) REFERENCES users(user_id)
		  ON UPDATE CASCADE ON DELETE SET NULL,
	  INDEX idx_loans_customer (customer_id),
	  INDEX idx_loans_status (status)
	) ENGINE=InnoDB;


	-- ─────────────────────────────────────────────────────────
	-- 9) REPORTS & AUDIT
	-- ─────────────────────────────────────────────────────────
	CREATE TABLE reports (
	  report_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
	  report_type   VARCHAR(60) NOT NULL,
	  generated_by  BIGINT NOT NULL,
	  file_path     VARCHAR(255) NOT NULL,
	  generated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  CONSTRAINT fk_reports_user
		FOREIGN KEY (generated_by) REFERENCES users(user_id)
		  ON UPDATE CASCADE ON DELETE RESTRICT
	) ENGINE=InnoDB;

	CREATE TABLE audit_logs (
	  log_id      BIGINT PRIMARY KEY AUTO_INCREMENT,
	  user_id     BIGINT NULL,
	  action      VARCHAR(80) NOT NULL,     -- LOGIN, ADD_BENEFICIARY, TRANSFER
	  entity      VARCHAR(80) NULL,         -- ACCOUNT, TRANSFER, LOAN, etc.
	  entity_id   BIGINT NULL,
	  ip_address  VARCHAR(45) NULL,         -- IPv4/IPv6
	  user_agent  VARCHAR(255) NULL,
	  details     JSON NULL,
	  created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  INDEX idx_audit_user_time (user_id, created_at),
	  INDEX idx_audit_action (action),
	  CONSTRAINT fk_audit_user
		FOREIGN KEY (user_id) REFERENCES users(user_id)
		  ON UPDATE CASCADE ON DELETE SET NULL
	) ENGINE=InnoDB;


ALTER TABLE customers 
  ADD COLUMN aadhaar_no VARCHAR(20) UNIQUE;

ALTER TABLE users 
  ADD COLUMN security_question VARCHAR(150),
  ADD COLUMN security_answer_hash VARCHAR(100);


-- new version

CREATE TABLE users (
    user_id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name      VARCHAR(100) NOT NULL,
    username       VARCHAR(60)  NOT NULL,
    password_hash  VARCHAR(100) NOT NULL,  -- bcrypt hashed password
    email          VARCHAR(120) NOT NULL,
    phone          VARCHAR(20)  NOT NULL,
    dob            DATE NOT NULL,
    gender         VARCHAR(10) NOT NULL,
    address        VARCHAR(255) NOT NULL,
    aadhaar        VARCHAR(12) UNIQUE NOT NULL,
    pan            VARCHAR(10) UNIQUE NOT NULL,
    account_type   VARCHAR(20) NOT NULL,
    deposit        DECIMAL(10,2) DEFAULT 0.00,
    role           ENUM('CUSTOMER','ADMIN','AUDITOR') NOT NULL DEFAULT 'CUSTOMER',
    status         ENUM('ACTIVE','SUSPENDED','CLOSED') NOT NULL DEFAULT 'ACTIVE',
    last_login     DATETIME NULL,
    created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_users_username (username),
    UNIQUE KEY uk_users_email    (email),
    UNIQUE KEY uk_users_phone    (phone)
) ENGINE=InnoDB;
