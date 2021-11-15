package gilko.marcin.Auctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.Auctions.model.auction_participant.AuctionParticipant;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipantId;

public interface AuctionParticipantRepository extends JpaRepository<AuctionParticipant, AuctionParticipantId>{
	AuctionParticipant findByPrimaryKey(AuctionParticipantId auctionParticipantId);


}
