-- Sample Customer Data Inserts
-- File: customer_sample_data.sql

-- Insert sample customers with diverse profiles
INSERT INTO customers (cpf, name, email, phone, monthly_income, credit_score, created_at, active) VALUES

-- High credit score customers
('123456789', 'João Miguel Silva', 'joao.silva@email.com', '+55-11-99999-0101', 8500.00, 780, '2024-01-15 10:30:00', true),
('234567890', 'Sarah Elizabeth Santos', 'sarah.santos@email.com', '+55-11-99999-0102', 9200.00, 820, '2024-01-18 14:20:00', true),
('345678901', 'David Roberto Wilson', 'david.wilson@email.com', '+55-11-99999-0103', 7800.00, 760, '2024-01-22 09:15:00', true),

-- Medium credit score customers
('456789012', 'Maria Elena Rodriguez', 'maria.rodriguez@email.com', '+55-11-99999-0104', 5500.00, 680, '2024-02-01 11:45:00', true),
('567890123', 'Tiago Thomas Anderson', 'tiago.anderson@email.com', '+55-11-99999-0105', 6200.00, 720, '2024-02-03 16:30:00', true),
('678901234', 'Jennifer Lisa Brown', 'jennifer.brown@email.com', '+55-11-99999-0106', 4800.00, 650, '2024-02-05 13:20:00', true),
('789012345', 'Miguel Antonio Davis', 'miguel.davis@email.com', '+55-11-99999-0107', 7200.00, 700, '2024-02-08 10:10:00', true),
('890123456', 'Lisa Marie Garcia', 'lisa.garcia@email.com', '+55-11-99999-0108', 5900.00, 690, '2024-02-10 12:40:00', true),

-- Lower credit score customers
('901234567', 'Roberto Carlos Miller', 'roberto.miller@email.com', '+55-11-99999-0109', 4200.00, 580, '2024-02-12 15:25:00', true),
('012345678', 'Amanda Nicole Taylor', 'amanda.taylor@email.com', '+55-11-99999-0110', 3800.00, 550, '2024-02-15 09:30:00', true),
('123450789', 'Cristopher Paulo Martinez', 'cristopher.martinez@email.com', '+55-11-99999-0111', 4500.00, 620, '2024-02-18 11:15:00', true),
('234561890', 'Jessica Lynn Thompson', 'jessica.thompson@email.com', '+55-11-99999-0112', 3200.00, 480, '2024-02-20 14:50:00', true),

-- High income customers
('345672901', 'William James White', 'william.white@email.com', '+55-11-99999-0113', 12500.00, 750, '2024-02-22 16:20:00', true),
('456783012', 'Emily Rose Clark', 'emily.clark@email.com', '+55-11-99999-0114', 11800.00, 790, '2024-02-25 10:45:00', true),
('567894123', 'Daniel Scott Lewis', 'daniel.lewis@email.com', '+55-11-99999-0115', 13200.00, 810, '2024-02-28 13:30:00', true),

-- Young professionals
('678905234', 'Ashley Michelle Lee', 'ashley.lee@email.com', '+55-11-99999-0116', 4800.00, 640, '2024-03-01 09:20:00', true),
('789016345', 'Brandon Kyle Walker', 'brandon.walker@email.com', '+55-11-99999-0117', 5200.00, 660, '2024-03-03 11:40:00', true),
('890127456', 'Stephanie Anne Hall', 'stephanie.hall@email.com', '+55-11-99999-0118', 4600.00, 620, '2024-03-05 14:15:00', true),

-- Senior customers
('901238567', 'Ricardo Frank Allen', 'ricardo.allen@email.com', '+55-11-99999-0119', 6800.00, 740, '2024-03-08 10:25:00', true),
('012349678', 'Patricia Ana Young', 'patricia.young@email.com', '+55-11-99999-0120', 5400.00, 710, '2024-03-10 12:50:00', true),

-- Customers with varying profiles
('123451789', 'Kevin Marcos Hernandez', 'kevin.hernandez@email.com', '+55-11-99999-0121', 5800.00, 670, '2024-03-12 15:35:00', true),
('234562890', 'Rachel Hope King', 'rachel.king@email.com', '+55-11-99999-0122', 6500.00, 720, '2024-03-15 09:45:00', true),
('345673901', 'André Steven Wright', 'andre.wright@email.com', '+55-11-99999-0123', 7200.00, 680, '2024-03-18 11:20:00', true),
('456784012', 'Nicole Marie Lopez', 'nicole.lopez@email.com', '+55-11-99999-0124', 4900.00, 590, '2024-03-20 13:40:00', true),
('567895123', 'Jonathan David Hill', 'jonathan.hill@email.com', '+55-11-99999-0125', 8200.00, 760, '2024-03-22 16:10:00', true),

-- Self-employed customers
('678906234', 'Samantha Joy Green', 'samantha.green@email.com', '+55-11-99999-0126', 6200.00, 650, '2024-03-25 10:30:00', true),
('789017345', 'Mateus Ryan Adams', 'mateus.adams@email.com', '+55-11-99999-0127', 9500.00, 720, '2024-03-28 12:15:00', true),
('890128456', 'Brittany Dawn Baker', 'brittany.baker@email.com', '+55-11-99999-0128', 5700.00, 680, '2024-03-30 14:25:00', true),

-- Recent graduates
('901239567', 'Tyler Tiago Gonzalez', 'tyler.gonzalez@email.com', '+55-11-99999-0129', 3800.00, 600, '2024-04-02 09:50:00', true),
('012340678', 'Megan Claire Nelson', 'megan.nelson@email.com', '+55-11-99999-0130', 4200.00, 640, '2024-04-05 11:30:00', true),
('123452789', 'Justin Miguel Carter', 'justin.carter@email.com', '+55-11-99999-0131', 3900.00, 580, '2024-04-08 13:45:00', true),

-- High-risk customers (lower scores)
('234563890', 'Crystal Lynn Mitchell', 'crystal.mitchell@email.com', '+55-11-99999-0132', 3200.00, 420, '2024-04-10 15:20:00', true),
('345674901', 'Marcus Antonio Perez', 'marcus.perez@email.com', '+55-11-99999-0133', 2800.00, 380, '2024-04-12 10:40:00', true),
('456785012', 'Tiffany Rose Roberts', 'tiffany.roberts@email.com', '+55-11-99999-0134', 3500.00, 450, '2024-04-15 12:25:00', true),

-- Premium customers
('567896123', 'Alexandre João Turner', 'alexandre.turner@email.com', '+55-11-99999-0135', 15200.00, 850, '2024-04-18 14:30:00', true),
('678907234', 'Victoria Grace Phillips', 'victoria.phillips@email.com', '+55-11-99999-0136', 14800.00, 830, '2024-04-20 16:15:00', true),

-- Brazilian customers
('789018345', 'Carlos Eduardo Silva', 'carlos.silva@email.com', '+55-11-99999-0137', 6800.00, 690, '2024-04-22 09:35:00', true),
('890129456', 'Ana Sofia Petrov', 'ana.petrov@email.com', '+55-11-99999-0138', 7500.00, 720, '2024-04-25 11:50:00', true),

-- Customers with no email (testing edge cases)
('901230567', 'Jorge William Evans', NULL, '+55-11-99999-0139', 5200.00, 630, '2024-04-28 13:20:00', true),
('012341678', 'Helena Marie Edwards', NULL, '+55-11-99999-0140', 4800.00, 610, '2024-05-01 15:40:00', true),

-- Recently registered customers
('123453789', 'Nathan Cole Stewart', 'nathan.stewart@email.com', '+55-11-99999-0141', 5600.00, 670, NOW() - INTERVAL '5 days', true),
('234564890', 'Alexis Faith Sanchez', 'alexis.sanchez@email.com', '+55-11-99999-0142', 4400.00, 590, NOW() - INTERVAL '3 days', true),
('345675901', 'Ryan Patrick Morris', 'ryan.morris@email.com', '+55-11-99999-0143', 6900.00, 710, NOW() - INTERVAL '1 day', true),

-- Inactive customers (for testing)
('456786012', 'Usuário Desativado Um', 'desativado1@email.com', '+55-11-99999-0144', 5000.00, 650, '2024-01-10 10:00:00', false),
('567897123', 'Usuário Desativado Dois', 'desativado2@email.com', '+55-11-99999-0145', 4500.00, 600, '2024-01-12 11:00:00', false);

-- Insert verification query (uncomment to run after inserts)
/*
SELECT
    COUNT(*) as total_customers,
    COUNT(CASE WHEN active = true THEN 1 END) as active_customers,
    COUNT(CASE WHEN active = false THEN 1 END) as inactive_customers,
    AVG(credit_score) as avg_credit_score,
    AVG(monthly_income) as avg_monthly_income,
    MIN(credit_score) as min_credit_score,
    MAX(credit_score) as max_credit_score,
    MIN(monthly_income) as min_income,
    MAX(monthly_income) as max_income
FROM customers;
*/

-- Credit score distribution query (uncomment to run)
/*
SELECT
    CASE
        WHEN credit_score >= 800 THEN 'Excellent (800+)'
        WHEN credit_score >= 740 THEN 'Very Good (740-799)'
        WHEN credit_score >= 670 THEN 'Good (670-739)'
        WHEN credit_score >= 580 THEN 'Fair (580-669)'
        ELSE 'Poor (< 580)'
    END as credit_range,
    COUNT(*) as customer_count,
    ROUND(AVG(monthly_income), 2) as avg_income
FROM customers
WHERE active = true
GROUP BY
    CASE
        WHEN credit_score >= 800 THEN 'Excellent (800+)'
        WHEN credit_score >= 740 THEN 'Very Good (740-799)'
        WHEN credit_score >= 670 THEN 'Good (670-739)'
        WHEN credit_score >= 580 THEN 'Fair (580-669)'
        ELSE 'Poor (< 580)'
    END
ORDER BY MIN(credit_score) DESC;
*/