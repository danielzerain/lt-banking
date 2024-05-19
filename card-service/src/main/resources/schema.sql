CREATE TABLE card_banking (
                              card_security_code int DEFAULT NULL,
                              create_date datetime(6) DEFAULT NULL,
                              update_date datetime(6) DEFAULT NULL,
                              account_id varchar(36) DEFAULT NULL,
                              id varchar(36) NOT NULL,
                              card_number varchar(255) DEFAULT NULL,
                              PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;