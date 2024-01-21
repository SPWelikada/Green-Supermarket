drop database if exists supermarket;
create database if not exists supermarket;
use supermarket;

CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contact VARCHAR(20),
    email VARCHAR(255),
    username VARCHAR(50),
    password VARCHAR(255)
);

CREATE TABLE admin (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(20),
      email VARCHAR(255),
      username VARCHAR(50),
      password VARCHAR(255)
);

CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    message TEXT,
    rating INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE helpdesk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    faqs TEXT,
    questions TEXT,
    answers TEXT,
    guidance TEXT
);

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    list_price DECIMAL(10,2),
    unit_price DECIMAL(10,2),
    qty INT,
    image_path VARCHAR(255)
);

CREATE TABLE shopping_cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    status ENUM('active', 'completed') DEFAULT 'active',
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE cart_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (cart_id) REFERENCES shopping_cart(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE `order` (
    id VARCHAR(255) PRIMARY KEY,
    customer_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'cancel', 'delivered') DEFAULT 'pending',
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE order_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id VARCHAR(255),
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES `order`(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
