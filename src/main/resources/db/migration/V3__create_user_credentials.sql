CREATE TABLE if not exists `user_credentials`(
                                      `user_credentials_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      `username` CHAR(255) NOT NULL,
                                      `password` CHAR(255) NOT NULL,
                                      `user_type_id` INT
);

ALTER TABLE
    `user_credentials` ADD CONSTRAINT `fk_3_user_type_id` FOREIGN KEY(`user_type_id`) REFERENCES `user_type`(`user_type_id`);