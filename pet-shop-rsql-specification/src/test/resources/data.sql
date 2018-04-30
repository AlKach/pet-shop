insert into public.users (id, name, login, password) values (1, 'User 1', 'login1', 'password1');
insert into public.users (id, name, login, password) values (2, 'User 2', 'login2', 'password2');
insert into public.users (id, name, login, password) values (3, 'User 3', 'login3', 'password3');
insert into public.users (id, name, login, password) values (4, 'User 4', 'login4', 'password4');

insert into public.categories (id, name) values (11, 'Category 1');
insert into public.categories (id, name) values (12, 'Category 2');

insert into public.products (id, name, description, price) values (101, 'Product 1', 'Description 1', 12);
insert into public.products (id, name, description, price) values (102, 'Product 2', 'Description 2', 34);
insert into public.products (id, name, description, price) values (103, 'Product 3', 'Description 3', 56);
insert into public.products (id, name, description, price) values (104, 'Product 4', 'Description 4', 78);

insert into public.products_categories (product_id, category_id) values (101, 11);
insert into public.products_categories (product_id, category_id) values (101, 12);
insert into public.products_categories (product_id, category_id) values (102, 12);
insert into public.products_categories (product_id, category_id) values (103, 11);

insert into public.orders (id, user_id) values (1001, 1);
insert into public.orders (id, user_id) values (1002, 1);
insert into public.orders (id, user_id) values (1003, 2);
insert into public.orders (id, user_id) values (1004, 3);
insert into public.orders (id, user_id) values (1005, 4);
insert into public.orders (id, user_id) values (1006, 4);

insert into public.orders_products (id, order_id, product_id, quantity) values (10001, 1001, 101, 1);
insert into public.orders_products (id, order_id, product_id, quantity) values (10002, 1001, 102, 3);
insert into public.orders_products (id, order_id, product_id, quantity) values (10003, 1002, 104, 2);
insert into public.orders_products (id, order_id, product_id, quantity) values (10004, 1003, 101, 4);
insert into public.orders_products (id, order_id, product_id, quantity) values (10005, 1004, 103, 1);
insert into public.orders_products (id, order_id, product_id, quantity) values (10006, 1004, 101, 4);
insert into public.orders_products (id, order_id, product_id, quantity) values (10007, 1005, 104, 5);
insert into public.orders_products (id, order_id, product_id, quantity) values (10008, 1006, 102, 5);
