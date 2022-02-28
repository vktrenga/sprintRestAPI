CREATE TABLE IF NOT EXISTS `user` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
     `first_name` varchar(40),
    `last_name` varchar(40),
    `email` varchar(50) NOT NULL UNIQUE,
    `dob` date DEFAULT NULL,
    `marital_status` boolean,
    `password` varchar(200) DEFAULT NULL,
    `otp_send_at` datetime DEFAULT NULL
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;