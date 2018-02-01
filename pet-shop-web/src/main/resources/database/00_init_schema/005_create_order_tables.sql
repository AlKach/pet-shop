CREATE TABLE orders (
    id NUMERIC(10,0) PRIMARY KEY,
    user_id NUMERIC(10,0) REFERENCES users(id) NOT NULL,
    date TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE orders_products (
    id NUMERIC(10,0) PRIMARY KEY,
    order_id NUMERIC(10,0) REFERENCES orders(id) NOT NULL,
    product_id NUMERIC(10,0) REFERENCES products(id) NOT NULL,
    quantity NUMERIC(10,0) DEFAULT 1 NOT NULL
);