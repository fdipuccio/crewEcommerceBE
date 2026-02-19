-- Database initialization script for e-commerce application

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Products Table
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    availability BOOLEAN DEFAULT TRUE
);

-- 3. Cart Table
CREATE TABLE IF NOT EXISTS cart (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id)
);

-- 4. Cart Items Table
CREATE TABLE IF NOT EXISTS cart_items (
    id SERIAL PRIMARY KEY,
    cart_id INT REFERENCES cart(id),
    product_id INT REFERENCES products(id),
    quantity INT NOT NULL CHECK (quantity > 0)
);

-- 5. Orders Table
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    total DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed Data for Users
INSERT INTO users (email, password) VALUES
('user1@example.com', 'hashed_password1'),
('user2@example.com', 'hashed_password2');

-- Seed Data for Products
INSERT INTO products (name, description, price) VALUES
('Product 1', 'Description for Product 1', 29.99),
('Product 2', 'Description for Product 2', 19.99);

