CREATE TABLE categories (
    id NUMERIC(10,0) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE products_categories (
    product_id NUMERIC(10,0) REFERENCES products(id),
    category_id NUMERIC(10,0) REFERENCES categories(id)
);