-- Create a table to store images as BLOBs (BYTEA)
CREATE TABLE IF NOT EXISTS stored_images (
    id VARCHAR(50) PRIMARY KEY,
    data BYTEA NOT NULL,
    mime_type VARCHAR(50) NOT NULL,
    filename VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
