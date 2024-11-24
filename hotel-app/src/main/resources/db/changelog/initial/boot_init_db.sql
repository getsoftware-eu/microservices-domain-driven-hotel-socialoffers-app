-- Create the schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS userschema;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
     id SERIAL PRIMARY KEY,
     username VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create roles table
CREATE TABLE IF NOT EXISTS roles (
     id SERIAL PRIMARY KEY,
     name VARCHAR(50) NOT NULL UNIQUE
);

-- Create users_roles table (many-to-many relation)
CREATE TABLE IF NOT EXISTS users_roles (
   user_id INT NOT NULL,
   role_id INT NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users(id),
   FOREIGN KEY (role_id) REFERENCES roles(id),
   PRIMARY KEY (user_id, role_id)
);

-- Create products table
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create orders table
CREATE TABLE IF NOT EXISTS orders (
      id SERIAL PRIMARY KEY,
      user_id INT NOT NULL,
      total_amount DECIMAL(10, 2) NOT NULL,
      order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create order_items table (many-to-many relation)
CREATE TABLE IF NOT EXISTS order_items (
       order_id INT NOT NULL,
       product_id INT NOT NULL,
       quantity INT NOT NULL,
       price DECIMAL(10, 2) NOT NULL,
       PRIMARY KEY (order_id, product_id),
       FOREIGN KEY (order_id) REFERENCES orders(id),
       FOREIGN KEY (product_id) REFERENCES products(id)
);
