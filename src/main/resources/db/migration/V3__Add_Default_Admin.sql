-- Add role column to admins and seed initial user
ALTER TABLE admins ADD COLUMN role VARCHAR(20) DEFAULT 'ADMIN';

INSERT INTO admins (username, password, full_name, email, role) 
VALUES ('admin', '$2a$10$8.UnVuG9.T2V.asYf8Y4aeXas7I0m7N/P1Uf9.8Y4aeXas7I0m7N/', 'System Administrator', 'admin@grafix.com', 'ADMIN');
