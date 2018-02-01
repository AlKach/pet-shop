CREATE TABLE products (
    id NUMERIC(10,0) PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price NUMERIC(10,3) DEFAULT 0 NOT NULL
);