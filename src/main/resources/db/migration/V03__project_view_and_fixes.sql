
CREATE OR REPLACE VIEW v_list_project AS
SELECT
    p.id as project_id
    p.name AS project_name,
    p.description AS project_description,
    p.status AS project_status,
    p.company_id as company_id
    (COUNT(CASE WHEN t.status = 'completed' THEN t.id END) / COUNT(t.id)) * 100 AS percentage
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