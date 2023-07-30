
CREATE OR REPLACE VIEW v_list_project AS
SELECT
    p.id as project_id,
    p.name AS project_name,
    p.description AS project_description,
    p.status AS project_status,
    p.company_id as company_id,
    (COUNT(CASE WHEN t.status = 'COMPLETED' THEN t.id END) / COUNT(t.id)) * 100 AS percentage
FROM 
    project p
LEFT JOIN 
    task t ON p.id = t.project_id
GROUP BY 
    p.id;

ALTER TABLE task MODIFY COLUMN status VARCHAR(255);

ALTER TABLE profile DROP COLUMN profile_pic;

ALTER TABLE client DROP COLUMN photo;

ALTER TABLE project ADD COLUMN brief VARCHAR(255) NULL;

ALTER TABLE note MODIFY COLUMN text LONGTEXT NOT NULL ;

ALTER TABLE `note`
    ADD COLUMN note_type ENUM('PERSONAL', 'PROJECT') NOT NULL;

-- Adicionar enum de status
ALTER TABLE `task`
    MODIFY COLUMN `status` ENUM('TO_DO', 'IN_PROGRESS', 'COMPLETED', 'MISSED_DEADLINE') NOT NULL,
    MODIFY COLUMN details LONGTEXT NOT NULL;

-- Criar evento para atualizar o status quando a data de vencimento for atingida
DELIMITER //
CREATE EVENT IF NOT EXISTS `update_task_status`
    ON SCHEDULE EVERY 1 DAY -- Intervalo para verificar uma vez por dia
    ON COMPLETION PRESERVE -- Manter o evento após a conclusão
    DO
    BEGIN
        UPDATE `task`
        SET `status` = 'MISSED_DEADLINE'
        WHERE `status` <> 'COMPLETED' AND `due_date` <= NOW();
    END //
DELIMITER ;

CREATE TABLE IF NOT EXISTS `invited_member` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `roles` VARCHAR(255) NOT NULL,
    `invite_message` TEXT NOT NULL,
    `activated` TINYINT(1) NOT NULL,
    `created_on` DATETIME NOT NULL
);

CREATE EVENT IF NOT EXISTS `delete_inactive_invites`
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    DELETE FROM `invited_member`
    WHERE `activated` = 0
    AND `created_on` < (NOW() - INTERVAL 3 DAY);
END;

CREATE VIEW project_info AS
SELECT
		p.id AS project_id,
    p.name AS project_name,
    p.description AS project_description,
    p.due_date as due_date,
    p.company_id as company_id,
    COUNT(CASE WHEN t.status = 'COMPLETED' THEN 1 END) AS num_tasks_completed,
    COUNT(CASE WHEN t.status = 'TO_DO' THEN 1 END) AS num_tasks_todo,
    DATEDIFF(p.due_date, CURDATE()) AS dias_restantes,
    COUNT(ta.id) AS num_membros,
		(COUNT(CASE WHEN t.status = 'COMPLETED' THEN 1 END) / COUNT(t.id)) * 100 AS conclusion_percentage

FROM
    project p
    LEFT JOIN task t ON p.id = t.project_id
    LEFT JOIN team ta ON p.id = ta.project_id
GROUP BY
    p.id;




