CREATE TABLE IF NOT EXISTS reservation (
   reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nickname VARCHAR(50) NOT NULL,
   student_name VARCHAR(50) NOT NULL,
   student_id BIGINT NOT NULL,
   phone_number VARCHAR(20) NOT NULL,
   purpose VARCHAR(255) NOT NULL,
   start_time DATETIME NOT NULL,
   end_time DATETIME NOT NULL
);