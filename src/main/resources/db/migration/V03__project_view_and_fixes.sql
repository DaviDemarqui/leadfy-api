
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

