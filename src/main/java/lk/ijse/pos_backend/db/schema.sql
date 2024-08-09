create
database pos_servlet_ijse;

use
pos_servlet_ijse;

create table customer
(
    id      varchar(40) primary key,
    name    varchar(50),
    address varchar(50)
);

create table item
(
    id    varchar(40) primary key,
    name  varchar(50),
    price decimal(6, 2),
    qty   int
);

create table orders
(
    id    varchar(40) primary key,
    date  date,
    total decimal(8, 2)
);

create table order_detail
(
    order_id   varchar(40),
    item_id    varchar(40),
    qty        int,
    unit_price decimal(8, 2),
    primary key (order_id, item_id),
    foreign key (order_id) references orders (id),
    foreign key (item_id) references item (id)
);

