CREATE TABLE productDB
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    type  VARCHAR(50)
);

CREATE TABLE orders
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    total     DECIMAL(10, 2) NOT NULL,
    orderDate TIMESTAMP      NOT NULL
);


CREATE TABLE orderItem
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    orderId   INT NOT NULL,
    productId INT NOT NULL,
    quantity  INT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders (id),
    FOREIGN KEY (productId) REFERENCES productDB (id)
);



