CREATE TABLE categories (
    id NUMERIC PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE products_categories (
    product_id NUMERIC REFERENCES products(id),
    category_id NUMERIC REFERENCES categories(id)
);