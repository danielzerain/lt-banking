CREATE TABLE user_banking (
                              create_date datetime(6) DEFAULT NULL,
                              update_date datetime(6) DEFAULT NULL,
                              id varchar(36) NOT NULL,
                              complete_name varchar(255) DEFAULT NULL,
                              id_type varchar(255) DEFAULT NULL,
                              identification_number varchar(255) DEFAULT NULL,
                              password varchar(255) DEFAULT NULL,
                              user_name varchar(255) DEFAULT NULL,
                              PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;