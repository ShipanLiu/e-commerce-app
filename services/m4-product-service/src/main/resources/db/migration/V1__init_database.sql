CREATE TABLE if not exists category
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500)
);


CREATE TABLE if not exists product
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(100),
    description        VARCHAR(500),
    available_quantity INT,
    price              DECIMAL(15, 2) NOT NULL,
    category_id        INT,
    -- When a category is deleted, any product linked to that category (via category_id) will also be automatically deleted from the product table.
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);