create table auctions(
id serial primary key,
description varchar(200) not null,
quantity int not null,
start_price double precision not null,
min_price double precision not null,
time int not null,
curr_price double precision,
last_bidder serial);