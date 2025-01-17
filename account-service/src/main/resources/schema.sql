CREATE TABLE IF NOT EXISTS account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255),
    confirmed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS account_credentials (
    account_id INT,
    CONSTRAINT account_credentials_fk FOREIGN KEY (account_id) REFERENCES account(id),
    password VARCHAR(255) NOT NULL,
    pin_code VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS account_settings (
    account_id INT,
    CONSTRAINT account_settings_fk FOREIGN KEY (account_id) REFERENCES account(id),
    locale VARCHAR(31) NOT NULL
);