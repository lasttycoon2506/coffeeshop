SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


CREATE TABLE IF NOT EXISTS Customers
(
    customer_id INT NOT NULL AUTO_INCREMENT UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS Employees
(
    employee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email  VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE, 
    title  VARCHAR(50) NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE TABLE IF NOT EXISTS Coffee
(
    coffee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    brand VARCHAR(50) NOT NULL,
    coffee_name  VARCHAR(50) NOT NULL,
    roast_type VARCHAR(50) NOT NULL,
    price  DECIMAL(13,2) NOT NULL, 
    region VARCHAR(50) NOT NULL,
    coffee_size int NOT NULL,
    PRIMARY KEY (coffee_id)
);

CREATE TABLE IF NOT EXISTS Orders
(
   orders_id INT NOT NULL AUTO_INCREMENT UNIQUE,
   orders_date DATE NOT NULL,
   customer_id INT NOT NULL,
   employee_id INT NOT NULL,
   order_status BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY(orders_id),
   CONSTRAINT FK_Orders_Customer_Id
   FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
   CONSTRAINT FK_Orders_Employee_Id
   FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) 
);

-- /*Intersection Table */ 

CREATE TABLE IF NOT EXISTS Items
(
   items_id INT NOT NULL AUTO_INCREMENT UNIQUE,
   quantity INT NOT NULL,
   coffee_id INT, 
   orders_id INT NOT NULL,
   PRIMARY KEY(items_id),
   CONSTRAINT FK_Items_Coffee_Id
   FOREIGN KEY (coffee_id) REFERENCES Coffee(coffee_id),
   CONSTRAINT FK_Orders_Order_Id
   FOREIGN KEY (orders_id) REFERENCES Orders(orders_id) ON DELETE CASCADE
);

-- /* Insertion of Data into Tables */

-- INSERT INTO Employees (first_name, last_name, email, phone, title) 
-- VALUES ('Johnny', 'Appleseed', 'johnnyapp@zippity.com', '111-111-1111', 'The Man'),
-- ('Weeping', 'Willow', 'mopey@gmail.com', '222-222-2222', 'Not the Man'),
-- ('Ludwig', 'Van', 'thebest@besty.com', '123-456-7890', 'Worker');

-- INSERT INTO Coffee (brand, coffee_name, roast_type, price, region, coffee_size) 
-- VALUES ('Islandy', 'Island Chill', 'Medium', 14.99, 'Indonesia', 14),
-- ('Kona', 'Wild Roast', 'Dark', 12.99, 'Ethiopia', 18),
-- ('Billys', 'Buck Roast', 'Light', 13.99, 'Columbia', 16);

-- INSERT INTO Orders (orders_date, customer_id, employee_id, order_status) 
-- VALUES ('2023-08-05', 1, 1, FALSE),
-- ('1975-5-5', 2, 2, FALSE),
-- ('2020-3-17', 3, 3, FALSE);

-- /* Intersection Table */

INSERT INTO Items (quantity, coffee_id, orders_id) 
VALUES (3, 2, 1),
(4, 1, 2),
(1, 3, 3);

SET FOREIGN_KEY_CHECKS=1;
COMMIT;

