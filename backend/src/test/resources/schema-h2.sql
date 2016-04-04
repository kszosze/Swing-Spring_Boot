create table if not exists customer(id identity primary key, code varchar(255), name varchar(255), phone varchar(255), valid boolean, phone2 varchar (255), address varchar(255), credit_limit real, credit real);
create table if not exists product(id identity primary key, code varchar(255), description varchar(255), price real, quantity int, valid boolean);
create table if not exists order_line(id identity primary key, sales_order_id bigint, product_id bigint, quantity int, valid boolean);
create table if not exists sales_order(id identity primary key, order_number bigint, customer_id bigint, total real, valid boolean);