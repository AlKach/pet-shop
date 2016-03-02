CREATE TABLE products (
    id NUMERIC PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price NUMERIC DEFAULT 0 NOT NULL
);