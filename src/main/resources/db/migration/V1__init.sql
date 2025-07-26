-- PostgreSQL Schema - Loan Simulation System (English)
-- File: schema.sql

-- 1. Customers Table
CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    cpf VARCHAR(9) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    monthly_income DECIMAL(15,2),
    credit_score INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN DEFAULT TRUE,

    CONSTRAINT chk_cpf_format CHECK (ssn ~ '^\d{9}$'),
    CONSTRAINT chk_credit_score_range CHECK (credit_score >= 300 AND credit_score <= 850),
    CONSTRAINT chk_income_positive CHECK (monthly_income >= 0)
);

-- Add comments to customers table
COMMENT ON TABLE customers IS 'Customer information and profile data';
COMMENT ON COLUMN customers.ssn IS 'cpf number - 9 digits';
COMMENT ON COLUMN customers.credit_score IS 'Credit score range 300-850';
COMMENT ON COLUMN customers.monthly_income IS 'Monthly income in R$';

-- 2. Simulations Table
CREATE TABLE IF NOT EXISTS simulations (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    customer_id BIGINT NOT NULL,
    requested_amount DECIMAL(15,2) NOT NULL,
    number_of_installments INTEGER NOT NULL,
    monthly_interest_rate DECIMAL(5,2) NOT NULL,
    installment_amount DECIMAL(15,2) NOT NULL,
    total_amount_to_pay DECIMAL(15,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    simulation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approved_date TIMESTAMP,
    approved_by VARCHAR(100),
    active BOOLEAN DEFAULT TRUE,

    CONSTRAINT uk_simulation_code UNIQUE  (code),
    CONSTRAINT fk_simulation_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT chk_amount_positive CHECK (requested_amount > 0),
    CONSTRAINT chk_installments_positive CHECK (number_of_installments > 0),
    CONSTRAINT chk_rate_positive CHECK (monthly_interest_rate >= 0),
    CONSTRAINT chk_status_valid CHECK (status IN ('PENDING', 'UNDER_REVIEW', 'APPROVED', 'REJECTED', 'CANCELLED', 'CONTRACTED'))
);

-- Add comments to loan_simulations table
COMMENT ON TABLE loan_simulations IS 'Loan simulation requests and calculations';
COMMENT ON COLUMN loan_simulations.requested_amount IS 'Amount requested by customer in USD';
COMMENT ON COLUMN loan_simulations.monthly_interest_rate IS 'Monthly interest rate as percentage';
COMMENT ON COLUMN loan_simulations.status IS 'Current status of the simulation';

-- 3. Installments Table (1:N relationship with Loan Simulations)
CREATE TABLE IF NOT EXISTS installments (
    id BIGSERIAL PRIMARY KEY,
    loan_simulation_id BIGINT NOT NULL,
    installment_number INTEGER NOT NULL,
    installment_amount DECIMAL(15,2) NOT NULL,
    interest_amount DECIMAL(15,2) NOT NULL,
    principal_amount DECIMAL(15,2) NOT NULL,
    remaining_balance DECIMAL(15,2) NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    paid_amount DECIMAL(15,2))

    CONSTRAINT fk_installment_simulation FOREIGN KEY (loan_simulation_id) REFERENCES loan_simulations(id) ON DELETE CASCADE,
    CONSTRAINT chk_installment_number_positive CHECK (installment_number > 0),
    CONSTRAINT chk_amounts_positive CHECK (
        installment_amount >= 0 AND
        interest_amount >= 0 AND
        principal_amount >= 0 AND
        remaining_balance >= 0
    ),
    CONSTRAINT uk_simulation_installment UNIQUE (loan_simulation_id, installment_number)
);

-- Add comments to installments table
COMMENT ON TABLE installments IS 'Payment schedule for approved loan simulations';
COMMENT ON COLUMN installments.installment_number IS 'Sequential number of the installment';
COMMENT ON COLUMN installments.interest_amount IS 'Interest portion of this installment';
COMMENT ON COLUMN installments.principal_amount IS 'Principal portion of this installment';
COMMENT ON COLUMN installments.remaining_balance IS 'Remaining loan balance after this payment';

-- 4. Performance Indexes
CREATE INDEX IF NOT EXISTS idx_customers_cpf ON customers(cpf);
CREATE INDEX IF NOT EXISTS idx_customers_active ON customers(active);
CREATE INDEX IF NOT EXISTS idx_customers_credit_score ON customers(credit_score);
CREATE INDEX IF NOT EXISTS idx_customers_created_at ON customers(created_at);

CREATE INDEX IF NOT EXISTS idx_simulation_code ON simulation(code);
CREATE INDEX IF NOT EXISTS idx_simulations_customer_id ON simulations(customer_id);
CREATE INDEX IF NOT EXISTS idx_simulations_status ON simulations(status);
CREATE INDEX IF NOT EXISTS idx_simulations_date ON simulations(simulation_date);
CREATE INDEX IF NOT EXISTS idx_simulations_active ON simulations(active);
CREATE INDEX IF NOT EXISTS idx_simulations_amount ON simulations(requested_amount);

CREATE INDEX IF NOT EXISTS idx_installments_simulation_id ON installments(loan_simulation_id);
CREATE INDEX IF NOT EXISTS idx_installments_due_date ON installments(due_date);
CREATE INDEX IF NOT EXISTS idx_installments_number ON installments(installment_number);
