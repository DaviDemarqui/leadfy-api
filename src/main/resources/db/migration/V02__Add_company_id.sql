
-- Add company_id column to task table
ALTER TABLE `task`
ADD COLUMN `company_id` BIGINT UNSIGNED NOT NULL;

-- Add company_id column to team table
ALTER TABLE `team`
ADD COLUMN `company_id` BIGINT UNSIGNED NOT NULL;

-- Add company_id column to client table
ALTER TABLE `client`
ADD COLUMN `company_id` BIGINT UNSIGNED NOT NULL;

-- Add company_id column to project table
ALTER TABLE `project`
ADD COLUMN `company_id` BIGINT UNSIGNED NOT NULL;

-- Add company_id column to note table
ALTER TABLE `note`
ADD COLUMN `company_id` BIGINT UNSIGNED NOT NULL;
