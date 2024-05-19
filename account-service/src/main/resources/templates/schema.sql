CREATE TABLE account_banking (
                                 balance double DEFAULT NULL,
                                 create_date datetime(6) DEFAULT NULL,
                                 creation_date datetime(6) DEFAULT NULL,
                                 update_date datetime(6) DEFAULT NULL,
                                 id varchar(36) NOT NULL,
                                 id_user_banking varchar(36) DEFAULT NULL,
                                 account_number varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;