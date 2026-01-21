-- Initial Schema for Grafix Professional Website

-- Business Information
CREATE TABLE business_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    studio_name VARCHAR(100),
    tag_line VARCHAR(200),
    about_description TEXT,
    owner_name VARCHAR(100),
    contact_email VARCHAR(100),
    contact_phone VARCHAR(50),
    address VARCHAR(255),
    facebook_url VARCHAR(255),
    instagram_url VARCHAR(255),
    twitter_url VARCHAR(255)
);

-- Admin Users
CREATE TABLE admins (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price VARCHAR(20) NOT NULL,
    description TEXT,
    image_url TEXT,
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Blog Posts
CREATE TABLE blog_posts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image_url TEXT,
    author VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Staff Members
CREATE TABLE staff (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    image_url TEXT,
    bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Visitor Logs
CREATE TABLE visitor_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    visitor_ip VARCHAR(45),
    user_agent TEXT,
    visited_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Contact Messages
CREATE TABLE contact_messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Initial Data
INSERT INTO business_info (studio_name, tag_line, about_description, owner_name, contact_email, contact_phone, address) 
VALUES ('GRAFIX STUDIO', 'Crafting Digital Excellence', 'We are a premier creative studio dedicated to making your vision a reality.', 'John Doe', 'hello@grafix.com', '+1 234 567 890', '123 Creative Street, Design City');

INSERT INTO staff (name, role, bio, image_url)
VALUES ('Alex Rivera', 'Creative Director', 'Passionate about minimalist design and bold typography.', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d'),
       ('Sarah Jenkins', 'Lead Developer', 'Coding master with a love for clean architecture.', 'https://images.unsplash.com/photo-1494790108377-be9c29b29330');
