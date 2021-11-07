create table auction(
auction_id serial primary key,
owner integer not null,
name varchar(50) not null,
description varchar(200) not null,
quantity int not null,
start_price double precision not null,
min_price double precision not null,
time int not null,
curr_price double precision,
last_bidder integer,
start_time timestamp);