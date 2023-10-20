CREATE TABLE productDB
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    type  VARCHAR(50),
    is_promotion BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE order
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    total     DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT 'total order amount',
    orderDate TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'order date',
    complete BOOLEAN        NOT NULL DEFAULT TRUE COMMENT 'true: in process, false: done'
);


CREATE TABLE orderItem
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    orderId   INT NOT NULL,
    productId INT NOT NULL,
    quantity  INT NOT NULL DEFAULT 1 COMMENT 'quantity of products in the order item',
    FOREIGN KEY (orderId) REFERENCES order (id) ON DELETE CASCADE,
    FOREIGN KEY (productId) REFERENCES productDB (id)
);



