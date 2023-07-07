CREATE TABLE `user`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `cpf` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL
);
CREATE TABLE `company`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `logo` BLOB NULL,
    `email` VARCHAR(255) NOT NULL,
    `industry` VARCHAR(255) NULL,
    `phone` VARCHAR(255) NOT NULL,
    `created` DATETIME NOT NULL,
    `cnpj` BIGINT NOT NULL,
    `address` VARCHAR(255) NOT NULL
);
CREATE TABLE `profile`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NULL,
    `email` VARCHAR(255) NOT NULL,
    `company_role` VARCHAR(255) NULL,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `privileges` BIGINT NULL,
    `profile_pic` BLOB NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE `review`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `rating` INT NOT NULL,
    `comment` VARCHAR(255) NOT NULL,
    `review_date` DATETIME NOT NULL,
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE `staff_profile`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `created` DATETIME NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL,
    `profile_pic` BLOB NULL,
    `user_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE `service`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `duration` INT NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL,
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `staff_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE `appointment`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `start_time` DATETIME NOT NULL,
    `end_time` DATETIME NOT NULL,
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `service_id` BIGINT UNSIGNED NOT NULL,
    `staff_id` BIGINT UNSIGNED NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE `privilegies`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);
CREATE TABLE `customer_profile`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `created` DATETIME NOT NULL,
    `profile_pic` BLOB NULL,
    `user_id` BIGINT UNSIGNED NOT NULL
);
ALTER TABLE
    `appointment` ADD CONSTRAINT `appointment_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `staff_profile` ADD CONSTRAINT `staff_profile_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `customer_profile` ADD CONSTRAINT `customer_profile_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `staff_profile` ADD CONSTRAINT `staff_profile_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `review` ADD CONSTRAINT `review_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `service` ADD CONSTRAINT `service_customer_id_foreign` FOREIGN KEY(`customer_id`) REFERENCES `customer_profile`(`id`);
ALTER TABLE
    `service` ADD CONSTRAINT `service_staff_id_foreign` FOREIGN KEY(`staff_id`) REFERENCES `staff_profile`(`id`);
ALTER TABLE
    `service` ADD CONSTRAINT `service_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `profile` ADD CONSTRAINT `profile_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `review` ADD CONSTRAINT `review_customer_id_foreign` FOREIGN KEY(`customer_id`) REFERENCES `customer_profile`(`id`);
ALTER TABLE
    `appointment` ADD CONSTRAINT `appointment_customer_id_foreign` FOREIGN KEY(`customer_id`) REFERENCES `customer_profile`(`id`);
ALTER TABLE
    `profile` ADD CONSTRAINT `profile_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `appointment` ADD CONSTRAINT `appointment_staff_id_foreign` FOREIGN KEY(`staff_id`) REFERENCES `staff_profile`(`id`);