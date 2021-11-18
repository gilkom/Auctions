create table auction_participant(
participant_id integer references participant,
auction_id integer references auction,
active boolean,
constraint auction_participant_pkey primary key(participant_id, auction_id));