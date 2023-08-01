CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` BIGINT UNSIGNED NOT NULL,
  `role_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `role`(`id`) ON DELETE CASCADE
);

ALTER TABLE `role`
ADD COLUMN `type` VARCHAR(10) NOT NULL;

INSERT INTO `role` (`name`, `type`)
VALUES
    ('ROLE_CLIENT', 'support'),
    ('ROLE_CLIENT_READ', 'user'),
    ('ROLE_CLIENT_CREATE', 'user'),
    ('ROLE_CLIENT_UPDATE', 'user'),
    ('ROLE_CLIENT_DELETE', 'user'),
    ('ROLE_TASK', 'support'),
    ('ROLE_TASK_READ', 'user'),
    ('ROLE_TASK_CREATE', 'user'),
    ('ROLE_TASK_UPDATE', 'user'),
    ('ROLE_TASK_DELETE', 'user'),
    ('ROLE_NOTE', 'support'),
    ('ROLE_NOTE_READ', 'user'),
    ('ROLE_NOTE_CREATE', 'user'),
    ('ROLE_NOTE_UPDATE', 'user'),
    ('ROLE_NOTE_DELETE', 'user'),
    ('ROLE_PROFILE', 'user'),
    ('ROLE_PROJECT', 'support'),
    ('ROLE_PROJECT_READ', 'user'),
    ('ROLE_PROJECT_CREATE', 'user'),
    ('ROLE_PROJECT_UPDATE', 'user'),
    ('ROLE_PROJECT_DELETE', 'user'),
    ('ROLE_TEAM_MEMBER', 'support'),
    ('ROLE_TEAM_MEMBER_READ', 'user'),
    ('ROLE_TEAM_MEMBER_CREATE', 'user'),
    ('ROLE_TEAM_MEMBER_UPDATE', 'user'),
    ('ROLE_TEAM_MEMBER_DELETE', 'user');

ALTER TABLE `project`
    ADD COLUMN `type` VARCHAR(255) NOT NULL AFTER `status`,
    ADD COLUMN `manager` BIGINT UNSIGNED NOT NULL AFTER `company_id`,
    ADD COLUMN `created_by` BIGINT UNSIGNED NOT NULL AFTER `manager`
    ADD COLUMN `time_tracking` TINYINT(1) NOT NULL DEFAULT FALSE,
    CHANGE COLUMN `due_date` `due_date` DATE NULL,
    CHANGE COLUMN `created_on` `created_on` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    DROP COLUMN `ongoing`;

ALTER TABLE `client`
    CHANGE COLUMN `project_id` `project_id` BIGINT UNSIGNED NULL,
    ADD COLUMN `created_by` BIGINT UNSIGNED NOT NULL AFTER `manager`;