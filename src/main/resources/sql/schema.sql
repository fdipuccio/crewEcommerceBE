-- DDL Scripts for Database Tables

-- 1. Users Table
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Categories Table
CREATE TABLE Categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);

-- 3. Products Table
CREATE TABLE Products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category_id INT REFERENCES Categories(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Carts Table
CREATE TABLE Carts (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. CartItems Table
CREATE TABLE CartItems (
    id SERIAL PRIMARY KEY,
    cart_id INT REFERENCES Carts(id) ON DELETE CASCADE,
    product_id INT REFERENCES Products(id) ON DELETE CASCADE,
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. Orders Table
CREATE TABLE Orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending'
);

-- 7. OrderItems Table
CREATE TABLE OrderItems (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES Orders(id) ON DELETE CASCADE,
    product_id INT REFERENCES Products(id) ON DELETE CASCADE,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10, 2) NOT NULL
);

-- Indexes for Optimized Queries
CREATE INDEX idx_users_username ON Users(username);
CREATE INDEX idx_products_category ON Products(category_id);
CREATE INDEX idx_orders_user ON Orders(user_id);
CREATE INDEX idx_cart_items_cart ON CartItems(cart_id);
CREATE INDEX idx_order_items_order ON OrderItems(order_id);

-- Seed Data for Categories
INSERT INTO Categories (name, description) VALUES
('Smartphones', 'Latest smartphones from top brands'),
('Accessories', 'Mobile accessories like cases, chargers, etc.'),
('Audio Products', 'Audio devices including headphones and speakers');

-- Seed Data for Products
INSERT INTO Products (name, description, price, category_id) VALUES
('iPhone 14', 'Latest Apple smartphone with A15 chip', 999.99, 1),
('Samsung Galaxy S21', 'High-performance Android smartphone', 799.99, 1),
('Google Pixel 6', 'Smartphone with exceptional camera', 599.99, 1),
('Anker PowerCore', 'Portable charger for smartphones', 29.99, 2),
('OtterBox Defender Case', 'Durable phone case for protection', 49.99, 2),
('Apple AirPods Pro', 'Wireless noise-canceling earbuds', 249.99, 3),
('Bose SoundLink', 'Portable Bluetooth speaker with great sound', 199.99, 3),
('Sony WH-1000XM4', 'Wireless noise-canceling over-ear headphones', 349.99, 3),
('Samsung Galaxy Buds', 'Compact true wireless earbuds', 149.99, 3),
('JBL Flip 5', 'Waterproof portable Bluetooth speaker', 89.99, 3);

-- Data Integrity Constraints and Validation Rules

-- Adding constraints for ensuring data integrity
ALTER TABLE Products ADD CONSTRAINT chk_price CHECK (price > 0);
ALTER TABLE Orders ADD CONSTRAINT chk_total CHECK (total >= 0);

-- Database Schema Documentation
-- Users: Contains user information for authentication and management.
-- Categories: Contains product categories for better organization.
-- Products: Contains product details including price and category.
-- Carts: Represents user shopping carts.
-- CartItems: Items within a user's cart with quantities.
-- Orders: Records transactions made by users.
-- OrderItems: Items within an order with quantities and prices.

-- Query Performance Optimization Recommendations
-- 1. Use indexes on frequently searched columns (e.g., username, category_id).
-- 2. Analyze query execution plans to identify bottlenecks.
-- 3. Implement caching for read-heavy operations to reduce database load.
-- 4. Regularly vacuum and analyze tables to maintain performance.
-- 5. Consider partitioning large tables if they grow significantly in size.

-- After implementing this schema, you can use tools like pgAdmin or DBeaver to visualize the schema and run queries as needed.
