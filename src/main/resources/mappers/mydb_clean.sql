CREATE DATABASE IF NOT EXISTS mydb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE mydb;

CREATE TABLE User (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO User (id, name, password) VALUES
(1, 'standard_user', 'secret_sauce'),
(2, 'locked_out_user', 'secret_sauce'),
(4, 'visual_user', 'secret_sauce');

CREATE TABLE UserOrder (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT DEFAULT NULL,
  order_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY user_id (user_id),
  CONSTRAINT userorder_ibfk_1 FOREIGN KEY (user_id) REFERENCES User (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO UserOrder (id, user_id, order_date) VALUES
(1, 1, '2025-01-31 09:43:18'),
(2, 1, '2025-01-31 09:43:18'),
(3, 2, '2025-01-31 09:43:18'),
(4, 4, '2025-01-31 09:43:18');

CREATE TABLE Product (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

INSERT INTO Product (id, name, price) VALUES
(1, 'Sauce Labs Backpack', 29.99),
(2, 'Sauce Labs Bike Light', 9.99),
(3, 'Sauce Labs Bolt T-Shirt', 15.99),
(4, 'Sauce Labs Fleece Jacket', 49.99),
(5, 'Sauce Labs Onesie', 7.99);

CREATE TABLE Order_Product (
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT DEFAULT 1,
  PRIMARY KEY (order_id, product_id),
  KEY product_id (product_id),
  CONSTRAINT order_product_ibfk_1 FOREIGN KEY (order_id) REFERENCES UserOrder (id),
  CONSTRAINT order_product_ibfk_2 FOREIGN KEY (product_id) REFERENCES Product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO Order_Product (order_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 4, 1),
(2, 2, 1),
(2, 5, 1),
(3, 5, 5),
(4, 2, 1);
