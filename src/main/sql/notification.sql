create table notification(
notification_id serial primary key,
participant_id integer references participant,
notification_time timestamp not null,
message varchar (200) not null);