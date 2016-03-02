CREATE TABLE orders (
    id NUMERIC PRIMARY KEY,
    user_id NUMERIC REFERENCES users(id) NOT NULL,
    date TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE orders_products (
    id NUMERIC PRIMARY KEY,
    order_id NUMERIC REFERENCES orders(id) NOT NULL,
    product_id NUMERIC REFERENCES products(id) NOT NULL,
    quantity NUMERIC DEFAULT 1 NOT NULL
);