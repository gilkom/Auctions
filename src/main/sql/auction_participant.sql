create table auction_participant(
participant_id integer references participant,
auction_id integer references auction);