CREATE TABLE IF NOT EXISTS `user`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS `company`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `industry` VARCHAR(255) NULL,
    `phone` VARCHAR(255) NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `address` VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS `task`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `details` LONGTEXT NOT NULL,
    `priority` VARCHAR(255) NOT NULL,
    `created_by` BIGINT UNSIGNED NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `assign_to` BIGINT UNSIGNED NOT NULL,
    `due_date` DATETIME NOT NULL,
    `status` ENUM('TO_DO', 'IN_PROGRESS', 'COMPLETED', 'MISSED_DEADLINE') NOT NULL,
    `project_id` BIGINT UNSIGNED NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE IF NOT EXISTS `profile`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NULL,
    `email` VARCHAR(255) NOT NULL,
    `company_role` VARCHAR(255) NOT NULL,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` TINYINT(1) NOT NULL
);
CREATE TABLE IF NOT EXISTS `team`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `project_id` BIGINT UNSIGNED NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE IF NOT EXISTS `team_member` (
    `profile_id` BIGINT UNSIGNED NOT NULL,
    `team_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`profile_id`, `team_id`),
    FOREIGN KEY (`profile_id`) REFERENCES `profile`(`id`),
    FOREIGN KEY (`team_id`) REFERENCES `team`(`id`)
);
CREATE TABLE IF NOT EXISTS `client`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `email` VARCHAR(255) NULL,
    `phone` VARCHAR(255) NOT NULL,
    `created_by` BIGINT UNSIGNED NOT NULL
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `project_id` BIGINT UNSIGNED NULL,
    `client_id` BIGINT UNSIGNED NULL,
    `company_id` BIGINT UNSIGNED NOT NULL;

);
CREATE TABLE IF NOT EXISTS `role`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS `project`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `due_date` DATETIME NOT NULL,
    `ongoing` TINYINT(1) NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` TINYINT(1) NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);
CREATE TABLE IF NOT EXISTS `note`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `text` LONGTEXT NOT NULL,
    `note_type` ENUM('PERSONAL', 'PROJECT') NOT NULL,
    `created_by` BIGINT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `project_id` BIGINT UNSIGNED NOT NULL,
    `company_id` BIGINT UNSIGNED NOT NULL
);

ALTER TABLE
    `profile` ADD CONSTRAINT `profile_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `client` ADD CONSTRAINT `client_project_id_foreign` FOREIGN KEY(`project_id`) REFERENCES `project`(`id`);
ALTER TABLE
    `note` ADD CONSTRAINT `note_project_id_foreign` FOREIGN KEY(`project_id`) REFERENCES `project`(`id`);
ALTER TABLE
    `task` ADD CONSTRAINT `task_project_id_foreign` FOREIGN KEY(`project_id`) REFERENCES `project`(`id`);
ALTER TABLE
    `profile` ADD CONSTRAINT `profile_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `project` ADD CONSTRAINT `project_company_id_foreign` FOREIGN KEY(`company_id`) REFERENCES `company`(`id`);
ALTER TABLE
    `project` ADD CONSTRAINT `project_client_id_foreign` FOREIGN KEY(`client_id`) REFERENCES `client`(`id`);
ALTER TABLE
    `team` ADD CONSTRAINT `team_project_id_foreign` FOREIGN KEY(`project_id`) REFERENCES `project`(`id`);